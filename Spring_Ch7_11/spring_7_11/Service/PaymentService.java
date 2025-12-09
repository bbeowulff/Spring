package com.example.spring_7_11.Service;

import com.example.spring_7_11.Exception.NotEnoughMoneyException;
import com.example.spring_7_11.Model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
