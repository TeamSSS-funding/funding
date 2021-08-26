package io.github.mygoodsupporter.domain.iamport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

    String imp_uid;


    String merchant_uid;


    String pay_method;


    String channel;


    String pg_provider;


    String pg_tid;


    boolean escrow;


    String apply_num;

    String bank_code;


    String bank_name;


    String card_code;


    String card_name;


    String card_number;


    int card_quota;


    int card_type;


    String vbank_code;


    String vbank_name;


    String vbank_num;


    String vbank_holder;


    long vbank_date;


    long vbank_issued_at;


    String name;


    int amount;


    int cancel_amount;


    String currency;


    String buyer_name;


    String buyer_email;


    String buyer_tel;


    String buyer_addr;


    String buyer_postcode;


    String custom_data;


    String status;


    long started_at;


    long paid_at;


    long failed_at;


    long cancelled_at;


    String fail_reason;


    String cancel_reason;


    String receipt_url;


    PaymentCancelDetail[] cancel_history;

    String[] cancel_receipt_urls;

    boolean cash_receipt_issued;

    String customer_uid;


    String customer_uid_usage;


}
