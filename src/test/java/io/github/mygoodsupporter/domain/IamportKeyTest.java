package io.github.mygoodsupporter.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IamportKeyTest {

    @Value("${iamport.restKey}")
    String restKey;
    @Value("${iamport.secretKey}")
    String secretKey;


}
