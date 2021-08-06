package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.security.MemberDetails;
import io.github.mygoodsupporter.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송지 추가 화면 요청
    @GetMapping(value="/users/deliveryWritePage")
    public String deliveryWrite() {
        return "users/deliveryWrite";
    }

    // 배송지 추가
    @PostMapping(value="/users/deliveryWrite")
    public String deliveryWrite(@AuthenticationPrincipal MemberDetails memberDetails, @ModelAttribute Address address) {
        address.setId(memberDetails.getId());
        int insertResult = deliveryService.deliveryWrite(address);
        if(insertResult > 0) {
            return "redirect:/users/deliveryList";
        } else {
            return "users/deliveryWrite";
        }
    }

    // 배송지 목록 조회
    @GetMapping(value="/users/deliveryList")
    public String deliveryList(@AuthenticationPrincipal MemberDetails memberDetails, @ModelAttribute Address address, Model model) {
        address.setId(memberDetails.getId());
        List<Address> addressList = deliveryService.addressList(address);
        model.addAttribute("addressList",addressList);
        return "users/deliveryList";
    }

    // 배송지 수정 화면 요청
    @GetMapping(value="/users/deliveryList/edit/{number}")
    public String addressUpdate(Model model, @PathVariable("number") int number) {
        Address address = deliveryService.addressUpdate(number);
        model.addAttribute("address",address);
        return "users/edit";
    }

    // 배송지 수정 처리
    @PostMapping(value = "/users/deliveryList/edit/{number}")
    public String editProcess(@ModelAttribute Address address) {
        int updateResult = deliveryService.editProcess(address);
        if(updateResult > 0) {
            return "redirect:/users/deliveryList";
        } else {
            return "users/edit";
        }
    }

    // 배송지 삭제
    @GetMapping(value="/users/deliveryList/delete/{number}")
    public String addressDelete(Model model, @PathVariable("number") int number) {
        int deleteResult = deliveryService.addressDelete(number);
        if(deleteResult > 0) {
            return "redirect:/users/deliveryList";
        } else {
            return "users/deliveryList";
        }
    }

}
