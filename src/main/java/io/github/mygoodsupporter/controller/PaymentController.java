package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Card;
import io.github.mygoodsupporter.dto.CardDTO;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.OrderService;
import io.github.mygoodsupporter.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    @GetMapping(value = "/profile")
    private String myPage() {
        return "myPage";
    }

    @GetMapping(value = "/profile/cards/new")
    private String paymentPage(@AuthenticationPrincipal UserDetails userDetails) {
        log.debug(userDetails.getId().toString());
        return "profile/newCard";
    }

    @PostMapping(value = "/profile/cards")
    private String cardRegister(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute CardDTO cardDTO) {
        cardDTO.setUserId(userDetails.getId());
        log.debug(userDetails.getId().toString());
        paymentService.createCard(cardDTO);
        return "redirect:/profile/cards";
    }

    @GetMapping(value = "/profile/cards")
    private String getCardList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Card> cardList = paymentService.getCardList(userDetails.getId());
        model.addAttribute("cardList", cardList);
        return "profile/cardlist";
    }

    @GetMapping(value = "/profile/cards/{cardId}")
    private String updateCardForm(@PathVariable("cardId") Long id, Model model){
        Card card = paymentService.getCardById(id);
        model.addAttribute("card",card);
        return "profile/card";

    }
    @PostMapping(value = "/profile/cards/{cardId}/edit")
    private String updateCard(@PathVariable("cardId") Long id, @ModelAttribute Card card) {
        card.setId(id);
        paymentService.updateCard(card);
        return "redirect:/profile/cards";
    }

    @PostMapping(value = "/profile/cards/{cardId}/delete")
    private String deleteCard(@PathVariable("cardId") Long id)  {
        paymentService.deleteCard(id);
        return "redirect:/profile/cards";

    }

    @GetMapping(value = "payments/selectedReward")
    private String selectedReward(){
        return "payments/selectedReward";
    }

    @PostMapping("/checkouts/new")
    public String createOrder(@RequestParam("rewardId") Long rewardId, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userDetails.getId();

        Long orderId = orderService.createOrder(userId, rewardId);

        String redirectUrl = "/checkouts/" + orderId.toString() + "/payments/new";
        return "redirect:" + redirectUrl;
    }

}
