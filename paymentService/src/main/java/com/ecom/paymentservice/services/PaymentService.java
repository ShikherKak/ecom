package com.ecom.paymentservice.services;

import com.ecom.paymentservice.models.Payment;
import com.ecom.paymentservice.models.PaymentGateway;
import com.ecom.paymentservice.models.PaymentStatus;
import com.ecom.paymentservice.paymentGateways.IPaymentGateway;
import com.ecom.paymentservice.paymentGateways.RazorPayPaymentGateway;
import com.ecom.paymentservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private IPaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService(IPaymentGateway paymentGateway,PaymentRepository paymentRepository)
    {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    public String createPaymentLink(Long orderId)
    {

        Long amount = 1000L;
        String userName = "Shikher Kak";
        String userEmail = "shikherkak@gmail.com";
        String userPhoneNumber = "7894561232";

        String paymentLink = paymentGateway.createPaymentLink(amount,userName,userEmail,userPhoneNumber,orderId);

        Payment payment = new Payment();
        payment.setPaymentLink(paymentLink);
        payment.setOrderId(orderId);
        payment.setPaymentGateway(PaymentGateway.RAZORPAY);
        payment.setAmount(amount);
        payment.setPaymentStatus(PaymentStatus.PENDING);

        paymentRepository.save(payment);

        return paymentLink;

    }

    public PaymentStatus getPaymentStatus(String paymentGatewayPaymentId)
    {
        Payment payment = paymentRepository.findByPaymentGatewayReferenceId(paymentGatewayPaymentId);

        PaymentStatus paymentStatus = paymentGateway.getPaymentStatus(paymentGatewayPaymentId);

        payment.setPaymentStatus(paymentStatus);

        paymentRepository.save(payment);

        return paymentStatus;

    }


}
