package io.github.mygoodsupporter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mygoodsupporter.domain.kakao.KakaoProfile;
import io.github.mygoodsupporter.domain.kakao.OAuthToken;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {

        return "users/login";
    }

    @GetMapping(value = "/login/oauth_kakao")
    public String KakaoCallback(String code) {

        OAuthToken oAuthToken = requestAccessToken(code);
        KakaoProfile kakaoProfile = requestKakaoProfile(oAuthToken);
        kakaoLogin(kakaoProfile);

        return "redirect:/";
    }

    private OAuthToken requestAccessToken(String code) {

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

        return oauthToken;

    }

    private KakaoProfile requestKakaoProfile(OAuthToken oAuthToken) {
        RestTemplate restTemplate = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ oAuthToken.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("property_keys", "kakao_account.email");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> KakaoProfileRequest =
                new HttpEntity<>(headers);

        //Http요청하기 - post방식 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                KakaoProfileRequest,
                String.class
        );

        log.debug(response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        //user오브젝트: username,password,email
        log.debug("카카오 아이디(번호):"+kakaoProfile.getId());
        log.debug("카카오 닉네임:"+kakaoProfile.getKakao_account().getProfile().getNickname());

        return kakaoProfile;
    }

    private void kakaoLogin(KakaoProfile kakaoProfile) {
        //       가입자 혹은 비가입자 체크해서 처리
        User originUser = userService.getUserByUsername(kakaoProfile.getId().toString());
        if(originUser == null) {
            UUID rubbishPassword = UUID.randomUUID();
            log.debug("마이서포터 패스워드:" + rubbishPassword);


            User kakaoUser = new User();
            kakaoUser.setUsername(String.valueOf(kakaoProfile.getId()));
            kakaoUser.setName(kakaoProfile.getKakao_account().getProfile().getNickname()+"_"+kakaoProfile.getId());
            kakaoUser.setPassword(rubbishPassword.toString());
            kakaoUser.setEmail(kakaoProfile.getKakao_account().getEmail());
            kakaoUser.setPhone("");
            log.debug("자동 회원가입 진행");
            userService.createUser(kakaoUser);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),rubbishPassword.toString()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            log.debug("이미 가입된 사용자");
            org.springframework.security.core.userdetails.UserDetails userDetails = new UserDetails(originUser);
            log.debug("userdetail정보"+ userDetails.getAuthorities());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    }

}
