package io.github.mygoodsupporter.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mygoodsupporter.domain.Order;
import io.github.mygoodsupporter.domain.iamport.AccessTokenResponse;
import io.github.mygoodsupporter.domain.iamport.IamportResponse;
import io.github.mygoodsupporter.domain.iamport.Payment;
import io.github.mygoodsupporter.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class IamportController {

    @Autowired
    OrderMapper orderMapper;

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private ObjectMapper mapper = new ObjectMapper();
    String restKey = "8126139556072178";
    String secretKey = "f9e54ede1c1976d03792bdbbf5d123c9c80848c5cd77cbfe69a33fd9bec5162d151408478fc8c8da";


    @RequestMapping("/verifyIamport/{imp_uid}")
    public String IamportCallback(@PathVariable String imp_uid, Order id){
        IamportResponse<AccessTokenResponse> accessToken = requestAccessToken();
        IamportResponse<Payment> billingInfo = paymentRequest(accessToken, imp_uid);
        //Order dbAmount = orderMapper.getById(id);
        int verifyAmount = 100;//dbAmount.getAmount();
        int iamportAmount = billingInfo.getResponse().getAmount();

        if(verifyAmount == iamportAmount){
            log.debug("verifyAmount[" + verifyAmount + "]" + "iamportAmount["
            +iamportAmount+"]" + "같아욧!!!");
            orderMapper.changeStatus(id);

        }

        return "payments/complete";
    }

    public IamportResponse<AccessTokenResponse> requestAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        System.out.println(headers);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("imp_key", restKey);
        params.add("imp_secret", secretKey);

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
