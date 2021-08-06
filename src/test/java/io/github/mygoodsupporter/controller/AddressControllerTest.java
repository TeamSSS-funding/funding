package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.WithCustomUserDetails;
import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.security.UserDetailsService;
import io.github.mygoodsupporter.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AddressController.class)
@WithCustomUserDetails
public class AddressControllerTest {

    @MockBean
    AddressService addressService;

    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAddresses() throws Exception {
        Long userId = 1L;
        given(addressService.getAddressesByUserId(anyLong())).
                willReturn(Arrays.asList(createAddress(userId), createAddress(userId)));

        mockMvc.perform(get("/profile/addresses"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("addressList"))
                .andExpect(view().name("address/AddressListPage"));
    }

    @Test
    void addAddress() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("city", "new city");
        params.add("street", "new street");
        params.add("zipcode", "new zipcode");

        mockMvc.perform(post("/profile/addresses").params(params))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/addresses"));
    }

    @Test
    void addAddressPage() throws Exception {
        mockMvc.perform(get("/profile/addresses/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("address/NewAddressPage"));
    }

    @Test
    void getAddress() throws Exception {
        Long addressId = 1L;
        given(addressService.getAddressById(addressId)).willReturn(createAddress());

        mockMvc.perform(get("/profile/addresses/" + addressId))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("address/AddressPage"));
    }

    @Test
    void editAddress() throws Exception {
        Long addressId = 1L;
        MultiValueMap<String , String> params = new LinkedMultiValueMap<>();
        params.add("city", "new city");
        params.add("street", "new street");
        params.add("zipcode", "new zipcode");

        mockMvc.perform(post("/profile/addresses/" + addressId + "/edit").params(params))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/addresses/" + addressId));
    }

    @Test
    void deleteAddress() throws Exception {
        Long addressId = 1L;
        mockMvc.perform(post("/profile/addresses/" + addressId+ "/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/addresses"));
    }

    public Address createAddress() {
        return Address.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();
    }

    public Address createAddress(Long userId) {
        return Address.builder()
                .userId(userId)
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();
    }
}