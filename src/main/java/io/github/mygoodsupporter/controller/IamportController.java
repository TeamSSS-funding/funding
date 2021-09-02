package io.github.mygoodsupporter.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mygoodsupporter.domain.Order;
import io.github.mygoodsupporter.domain.OrderStatus;
import io.github.mygoodsupporter.domain.iamport.AccessTokenResponse;
import io.github.mygoodsupporter.domain.iamport.IamportResponse;
import io.github.mygoodsupporter.domain.iamport.Payment;
import io.github.mygoodsupporter.mapper.OrderMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Slf4j
@Controller
public class IamportController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IamportProps iamportProps;


    @PostMapping("/verifyIamport/{imp_uid}")
    public ResponseEntity<String> IamportCallback(@RequestParam String imp_uid, @RequestParam String merchant_uid) throws JsonProcessingException {
        IamportResponse<AccessTokenResponse> accessToken = requestAccessToken();
        IamportResponse<Payment> billingInfo = paymentRequest(accessToken, imp_uid);
        Long orderId = Long.valueOf(merchant_uid);
        Order order = orderMapper.getOrderById(orderId);
        int amountToBePaid = order.getAmount();
        int amount = billingInfo.getResponse().getAmount();

        ObjectMapper objectMapper = new ObjectMapper();
        if(amountToBePaid != amount){
            TransactionStatement tst = new TransactionStatement(orderId.toString(), "Invalid amount");
            String json = objectMapper.writeValueAsString(tst);
            ResponseEntity<String> response = new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
            return response;
        }

        order.setOrderStatus(OrderStatus.SUCCEED);
        orderMapper.changeStatus(order);
        TransactionStatement tst = new TransactionStatement(orderId.toString(), "succeed");
        String json = objectMapper.writeValueAsString(tst);
        ResponseEntity<String> response = new ResponseEntity<>(json, HttpStatus.OK);
        return response;
    }


    @Getter @Setter
    class TransactionStatement implements Serializable {
        private String orderId;
        private String status;
        public TransactionStatement(String orderId, String status) {
            this.orderId = orderId;
            status = status;
        }
    }

    @GetMapping("/checkouts/{orderId}/payments/complete")
    public String orderPage(@PathVariable("orderId") Long orderId, Model model)
    {
        Order order = orderMapper.getOrderById(orderId);
        model.addAttribute("order", order);
        return "payments/complete";
    }

    public IamportResponse<AccessTokenResponse> requestAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        System.out.println(headers);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("imp_key", iamportProps.getRestKey());
        params.add("imp_secret", iamportProps.getSecretKey());

        HttpEntity<MultiValueMap<String, String>> iamportTokenRequest = new HttpEntity<>(params,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.iamport.kr/users/getToken",
                HttpMethod.POST,
                iamportTokenRequest,
                String.class
        );
        log.debug(String.valueOf(response));

        ObjectMapper objectMapper = new ObjectMapper();
        IamportResponse<AccessTokenResponse> accessTokenResponse = null;
        try {
            accessTokenResponse = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });

        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        assert accessTokenResponse != null;

        System.out.println(accessTokenResponse.getResponse().getAccess_token());

            return accessTokenResponse;
    }
        public IamportResponse<Payment> paymentRequest(IamportResponse<AccessTokenResponse> accessTokenResponse, String imp_uid){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessTokenResponse.getResponse().getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> BillingRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.iamport.kr/payments/" + imp_uid,
                HttpMethod.GET,
                BillingRequest,
                String.class
        );
            log.debug(String.valueOf(response));
            ObjectMapper objectMapper = new ObjectMapper();
            IamportResponse<Payment> billingInfo = null;

            try {
                billingInfo = objectMapper.readValue(response.getBody(), new TypeReference<>() {
                });
            } catch (JsonProcessingException e){
                e.printStackTrace();
            }

            return billingInfo;
    }
}
