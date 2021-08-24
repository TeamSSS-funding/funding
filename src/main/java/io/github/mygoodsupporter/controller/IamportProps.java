package io.github.mygoodsupporter.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class IamportProps {

    @Value("${iamport.restKey}")
    String restKey;
    @Value("${iamport.secretKey}")
    String secretKey;
}
