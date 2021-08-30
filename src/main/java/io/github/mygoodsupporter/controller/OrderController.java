package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.exception.NotYetImplementedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/checkouts/{orderId}/payments/new")
    public String confirmPage() {
        throw new NotYetImplementedException();
    }

}
