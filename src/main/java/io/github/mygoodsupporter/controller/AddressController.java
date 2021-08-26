package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.dto.AddressForm;
import io.github.mygoodsupporter.exception.AddressNotFoundException;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // 목록
    @GetMapping("/profile/addresses")
    public String getAddresses(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long userId = userDetails.getId();
        List<Address> addresses = addressService.getAddressesByUserId(userId);
        model.addAttribute("addressList", addresses);
        return "profile/addresslist";
    }

    // 등록 화면
    @GetMapping("/profile/addresses/new")
    public String addAddressPage(Model model) {
        model.addAttribute("AddressForm", new AddressForm());
        return "profile/createAddress";
    }

    // 등록
    @PostMapping("/profile/addresses")
    public String addAddress(@ModelAttribute("AddressForm") @Valid AddressForm form, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) {
        if(bindingResult.hasErrors()) {
            return "profile/createAddress";
        }
        Long userId = userDetails.getId();
        addressService.createAddress(userId, form.getName(), form.getPhone(), form.getPostcode(), form.getRoad(), form.getJibun(), form.getDetail(), form.getChamgo());
        return "redirect:/profile/addresses";
    }

    // 수정 요청
    @GetMapping("/profile/addresses/{addressId}")
    public String getAddress(@PathVariable("addressId") Long addressId, Model model) {
        Address address = addressService.getAddressById(addressId);
        AddressForm form = AddressForm.fromAddress(address);
//        model.addAttribute("address", address);
        model.addAttribute("addressForm", form);
        return "profile/updateAddress";
    }

    // 수정
    @PostMapping("/profile/addresses/{addressId}/edit")
    public String editAddress(@ModelAttribute("addressForm") @Valid AddressForm addressForm, BindingResult bindingResult,
                              @PathVariable("addressId") Long addressId) {
        if (bindingResult.hasErrors()) {
            return "profile/updateAddress";
        }
        addressService.updateAddress(addressId, addressForm.getName(), addressForm.getPhone(), addressForm.getPostcode(), addressForm.getRoad(), addressForm.getJibun(), addressForm.getDetail(), addressForm.getChamgo());
        String url = "/profile/addresses/"; // + addressId;
        return "redirect:" + url;
    }

    // 삭제
    @PostMapping("/profile/addresses/{addressId}/delete")
    public String deleteAddress(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddress(addressId);
        return "redirect:/profile/addresses";
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public String handleNotFoundException() {
        return "error/404";
    }

}
