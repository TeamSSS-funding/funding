package io.github.mygoodsupporter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.domain.OAuthToken;
import io.github.mygoodsupporter.domain.kakao.KakaoProfile;
import io.github.mygoodsupporter.dto.RegistrationForm;
import io.github.mygoodsupporter.security.MemberDetails;
import io.github.mygoodsupporter.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.*;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



import javax.validation.Valid;


import java.util.List;
import java.util.UUID;


@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthenticationManager authenticationManager;

    private final MemberService memberService;



    // 회원가입 페이지 이동
    @GetMapping(value="/joinPage")
    public String memberJoinPage(Model model) {

        model.addAttribute("registrationForm" ,new RegistrationForm());
        return "join";
    }


    // 회원가입 실행
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute RegistrationForm registrationForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.debug(fieldError.getDefaultMessage());
            }
            return "join";
        }

        Member member = registrationForm.toMember();
        int insertResult = memberService.join(member);

        if(insertResult>0) {
            return "users/login";
        }
        return "joinfail";
    }

    @RequestMapping(value="/idcheck")
    public @ResponseBody
    String idCheck(@RequestParam("id")String id) {
        return memberService.idCheck(id);
    }

    @GetMapping(value = "/login/oauth_kakao")
    public String KakaoCallback(String code) {
        RestTemplate restTemplate = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "631b84f94a27727f5c58546dfe62d1d3");
        params.add("redirect_uri", "http://localhost:8080/login/oauth_kakao");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> KakaoTokenRequest =
                new HttpEntity<>(params, headers);
        
        //Http요청하기 - post방식 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                KakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        log.debug("카카오 엑세스 토큰:"+ oauthToken.getAccess_token());

        RestTemplate restTemplate2 = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
        headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("property_keys", "kakao_account.email");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> KakaoProfileRequest =
                new HttpEntity<>(headers2);

        //Http요청하기 - post방식 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response2 = restTemplate2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                KakaoProfileRequest,
                String.class
        );

        log.debug(response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        //user오브젝트: username,password,email
        log.debug("카카오 아이디(번호):"+kakaoProfile.getId());
        log.debug("카카오 닉네임:"+kakaoProfile.getKakao_account().getProfile().getNickname());

        log.debug("마이서포터 유저네임:" +kakaoProfile.getClass().getName()+"_"+kakaoProfile.getId());
        UUID rubbishPassword = UUID.randomUUID();
        log.debug("마이서포터 패스워드:" + rubbishPassword);


        Member KakaoMember = new Member();
        KakaoMember.setId(String.valueOf(kakaoProfile.getId()));
        KakaoMember.setName(kakaoProfile.getKakao_account().getProfile().getNickname()+"_"+kakaoProfile.getId());
        KakaoMember.setPassword(rubbishPassword.toString());
        KakaoMember.setEmail(kakaoProfile.getKakao_account().getEmail());
        KakaoMember.setPhone("");


//       가입자 혹은 비가입자 체크해서 처리
        Member originUser = memberService.getMemberById(kakaoProfile.getId().toString());
        if(originUser == null) {
            log.debug("자동 회원가입 진행");
            memberService.join(KakaoMember);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(KakaoMember.getId(),rubbishPassword.toString()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            log.debug("이미 가입된 사용자");
            UserDetails userDetails = new MemberDetails(originUser);
            log.debug("userdetail정보"+ userDetails.getAuthorities());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        log.debug(KakaoMember.getPassword());
        //로그인처리


         return "redirect:/";


    }



}
