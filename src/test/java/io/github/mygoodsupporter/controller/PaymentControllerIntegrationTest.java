package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.WithCustomUserDetails;
import io.github.mygoodsupporter.domain.Card;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.mapper.PaymentMapper;
import io.github.mygoodsupporter.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
class PaymentControllerIntegrationTest {
   @Autowired
   UserService userService;

   @Autowired
   PaymentMapper paymentMapper;

   @Autowired
   MockMvc mockMvc;

   User user;
   Card card;

   @BeforeEach
   public void setup(){
      user = new User();
      user.setUsername("test");
      user.setPassword("test");
      user.setEmail("test@test.com");
      user.setName("test");
      user.setPhone("010-5511-6133");

      userService.createUser(user);

      card = new Card(4L,2L,"2222-2222-2222-2222", "12/25","12","900111");
      paymentMapper.insertCard(card);
   }


   @Test
   @WithCustomUserDetails
   public void cardRegisterTest() throws Exception{

      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

      params.add("cardNumber", "1111-1111-5555-5555");
      params.add("expiredDate", "12/26");
      params.add("cardPassword", "12");
      params.add("dateOfBirth","890803");

      this.mockMvc.perform(
              post("/profile/cards")
                      .params(params)
                      .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().is3xxRedirection())
              .andExpect(redirectedUrl("/profile/cards"))
              .andDo(print());


   }

   @Test
   @WithCustomUserDetails
   public void getCardListTest() throws Exception{
      mockMvc.perform(get("/profile/cards"))
              .andDo(print())
              .andExpect(status().is2xxSuccessful())
              .andExpect(model().attributeExists("cardList"))
              .andExpect(view().name("profile/cardlist"));
   }

   @Test
   @WithCustomUserDetails
   public void updateCardFormTest() throws Exception{

      MvcResult mvcResult = mockMvc.perform(get("/profile/cards/" + card.getId()))
              .andDo(print())
              .andExpect(status().is2xxSuccessful())
               .andExpect(model().attributeExists("card"))
               .andExpect(view().name("profile/card"))
               .andReturn();
   }

   @Test
   @WithCustomUserDetails
   public void updateCardTest() throws Exception{
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("id", card.getId().toString());
      params.add("userId", "1");
      params.add("cardNumber", "1111-1111-5555-5555");
      params.add("expiredDate", "12/26");
      params.add("cardPassword", "12");
      params.add("dateOfBirth","890803");

      mockMvc.perform(post("/profile/cards/" + card.getId() + "/edit")
              .params(params)
              .contentType(MediaType.APPLICATION_FORM_URLENCODED))
              .andDo(print())
              .andExpect(status().is3xxRedirection())
              .andExpect(redirectedUrl("/profile/cards"));
   }

   @Test
   @WithCustomUserDetails
   public void deleteCardTest() throws Exception{
      mockMvc.perform(post("/profile/cards/" + card.getId() + "/delete")
              .contentType(MediaType.APPLICATION_FORM_URLENCODED))
              .andDo(print())
              .andExpect(status().is3xxRedirection())
              .andExpect(redirectedUrl("/profile/cards"));
   }





}