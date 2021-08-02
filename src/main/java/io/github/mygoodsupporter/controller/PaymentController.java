package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Cardinfo;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(value="myPage")
    private String myPage(){
        return "myPage";
    }

    @GetMapping(value="profile/cards")
    private String paymentPage(@AuthenticationPrincipal UserDetails userDetails){
        log.debug(userDetails.getId().toString());
        return "profile/cards";
    }
    @PostMapping(value= "profile/cardRegister")
    private String cardRegister(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Cardinfo cardinfoProcess){
        cardinfoProcess.setUserId(userDetails.getId().toString());
        log.debug(userDetails.getId().toString());
        paymentService.cardRegister(cardinfoProcess);
        return "redirect:/profile/cardlist";
    }

    @GetMapping(value = "profile/cardlist")
    private String cardList(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Cardinfo cardinfo, Model model){
        cardinfo.setUserId(userDetails.getId().toString());
        List<Cardinfo> cardList = paymentService.cardList(cardinfo);

        model.addAttribute("cardList", cardList);
        return "profile/cardlist";
    }







}
