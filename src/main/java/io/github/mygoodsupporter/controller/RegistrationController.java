package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.dto.RegistrationForm;
import io.github.mygoodsupporter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    // 회원가입 페이지 이동
    @GetMapping(value="/joinPage")
    public String joinPage(Model model) {

        model.addAttribute("registrationForm" ,new RegistrationForm());
        return "join";
    }


    // 회원가입 실행
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute RegistrationForm registrationForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "join";
        }

        User user = registrationForm.toUser();
        userService.createUser(user);

        return "redirect:/login";
    }

    @RequestMapping(value="/idcheck")
    @ResponseBody
    public String isDuplicateUsername(@RequestParam("id")String username) {

        boolean isDuplicate = userService.isDuplicatedUsername(username);

        if(!isDuplicate) {
            return "no";
        }
        return "ok";
    }

}
