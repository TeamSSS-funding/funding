package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.dto.AddressForm;
import io.github.mygoodsupporter.exception.AddressNotFoundException;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/profile/addresses")
    public String getAddresses(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        Long userId = userDetails.getId();
        List<Address> addresses = addressService.getAddressesByUserId(userId);

        model.addAttribute("addressList", addresses);

        return "address/AddressListPage";
    }

    @PostMapping("/profile/addresses")
    public String addAddress(@Valid AddressForm form, BindingResult bindingResult,
                             @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            return "address/AddressPage";
        }

        Long userId = userDetails.getId();

        addressService.createAddress(userId, form.getCity(), form.getStreet(), form.getZipcode());

        return "redirect:/profile/addresses";
    }

    @GetMapping("/profile/addresses/new")
    public String addAddressPage(Model model) {

        model.addAttribute("address", new AddressForm());

        return "address/NewAddressPage";
    }

    @GetMapping("/profile/addresses/{addressId}")
    public String getAddress(@PathVariable("addressId") Long addressId, Model model) {

        Address address = addressService.getAddressById(addressId);

        model.addAttribute("address", address);

        return "address/AddressPage";
    }


    @PostMapping("/profile/addresses/{addressId}/edit")
    public String editAddress(@Valid AddressForm form, BindingResult bindingResult,
                              @PathVariable("addressId") Long addressId) {

        if (bindingResult.hasErrors()) {
            return "address/AddressPage";
        }

        addressService.updateAddress(addressId, form.getCity(), form.getStreet(), form.getZipcode());

        String url = "/profile/addresses/" + addressId;
        return "redirect:" + url;
    }

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
