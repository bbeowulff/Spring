package com.example.proxy;


import com.example.proxy.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentsController {

    private static final Logger logger =
            Logger.getLogger(PaymentsController.class.getName());

    private final PaymentsProxy paymentsProxy;

    public PaymentsController(PaymentsProxy paymentsProxy) {
        this.paymentsProxy = paymentsProxy;
    }

//    @PostMapping("/payment")
//    public Payment createPayment(@RequestBody Payment payment) {
//        logger.info("Proxy received payment: " + payment.getAmount());
//        return paymentsProxy.createPayment(payment);
//    }

//    @PostMapping("/payment")
//    public Payment createPayment(
//            @RequestBody Payment payment
//    ) {
//        String requestId = UUID.randomUUID().toString();
//        return paymentsProxy.createPayment(requestId, payment);
//    }

    @PostMapping("/payment")
    public Mono<Payment> createPayment(
            @RequestBody Payment payment
    ) {
        String requestId = UUID.randomUUID().toString();
        return paymentsProxy.createPayment(requestId, payment);
    }

}

