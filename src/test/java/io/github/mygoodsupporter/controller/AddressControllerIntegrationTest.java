package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.WithCustomUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@Sql("/fixture/controller/AddressControllerFixture.sql")
@WithCustomUserDetails
public class AddressControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAddresses() throws Exception {
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
}