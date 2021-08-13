package io.github.mygoodsupporter.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mygoodsupporter.domain.iamport.AccessTokenResponse;
import io.github.mygoodsupporter.domain.iamport.IamportResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class IamportController {

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private ObjectMapper mapper = new ObjectMapper();
    String restKey = "8126139556072178";
    String secretKey = "f9e54ede1c1976d03792bdbbf5d123c9c80848c5cd77cbfe69a33fd9bec5162d151408478fc8c8da";


    @RequestMapping("/payments/complete")
    public String IamportCallback(String imp_uid){
        log.debug(imp_uid);
        return imp_uid;
    }

    private void requestAccessToken(String restKey, String secretKey){
        IamportResponse<AccessTokenResponse> token = this.requestAccessToken(restKey,secretKey);
        String accessToken = token.getResponse().getAccessToken();
        httpHeaders.add("Authorization", accessToken);

    }





}
