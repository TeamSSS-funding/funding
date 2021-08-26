package io.github.mygoodsupporter.domain.iamport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCancelDetail {


    String pg_tid;


    int amount;


    long cancelled_at;


    String reason;


    String receipt_url;


}
