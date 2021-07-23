package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.dto.RegistrationForm;
import io.github.mygoodsupporter.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final MemberService memberService;

    // 회원가입 페이지 이동
    @GetMapping(value="/memberJoinPage")
    public String memberJoinPage(Model model) {

        model.addAttribute("registrationForm" ,new RegistrationForm());
        return "memberJoin";
    }


    // 회원가입 실행
    @PostMapping("/memberJoin")
    public String join(@Valid @ModelAttribute RegistrationForm registrationForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.debug(fieldError.getDefaultMessage());
            }
            return "memberJoin";
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
}
