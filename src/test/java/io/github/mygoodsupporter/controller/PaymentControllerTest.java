package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.WithCustomUserDetails;
import io.github.mygoodsupporter.domain.Card;
import io.github.mygoodsupporter.dto.CardDTO;
import io.github.mygoodsupporter.security.UserDetailsService;
import io.github.mygoodsupporter.service.PaymentService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PaymentController.class)
@WithCustomUserDetails
public class PaymentControllerTest {

    @MockBean
    PaymentService paymentService;

    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    private CardDTO cardDto;


    @Before
    public void setup(){


    }

    @Test
    void cardRegisterTest() throws Exception{
        Card card = new Card(1L,"5555-5555-5555-5555","12/25","13","550506");



        mockMvc.perform(post("/profile/cards"))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("card"))
                .andExpect(view().name("newCard"));
    }


}