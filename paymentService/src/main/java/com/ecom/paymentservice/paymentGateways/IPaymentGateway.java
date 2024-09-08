package com.ecom.paymentservice.paymentGateways;

import com.ecom.paymentservice.models.PaymentStatus;
import com.razorpay.RazorpayException;

public interface IPaymentGateway {

    String createPaymentLink(Long amount, String userName, String userEmail, String userPhoneNumber,Long orderId);

    PaymentStatus getPaymentStatus(String paymentId);
}
