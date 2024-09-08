package com.ecom.paymentservice.controllers;

import com.ecom.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.ecom.paymentservice.dtos.CreatePaymentLinkResponseDto;
import com.ecom.paymentservice.models.PaymentStatus;
import com.ecom.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }


    @PostMapping("")
    public CreatePaymentLinkResponseDto createPaymentLink(@RequestBody CreatePaymentLinkRequestDto createPaymentLinkRequestDto) {
        String url = paymentService.createPaymentLink(createPaymentLinkRequestDto.getOrderId());

        CreatePaymentLinkResponseDto createPaymentLinkResponseDto = new CreatePaymentLinkResponseDto();
        createPaymentLinkResponseDto.setUrl(url);
        return createPaymentLinkResponseDto;
    }

    @GetMapping("/{id}")
    public PaymentStatus checkPaymentStatus(@PathVariable("id") String paymentGatewayPaymentId){
        return paymentService.getPaymentStatus(paymentGatewayPaymentId);}

    public void handleWebhookEvent(){} //will be implemented later

}
