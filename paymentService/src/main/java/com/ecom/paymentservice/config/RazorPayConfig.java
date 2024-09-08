package com.ecom.paymentservice.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {

    @Value("${razorpay.key_id}")
    private String razorPayKeyID;

    @Value("${razorpay.key_secret}")
    private String razorPayKeyPassword;

    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        return new RazorpayClient(
                razorPayKeyID,
                razorPayKeyPassword);
    }


}