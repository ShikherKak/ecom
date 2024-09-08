package com.ecom.paymentservice.paymentGateways;

import com.ecom.paymentservice.models.PaymentStatus;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RazorPayPaymentGateway implements IPaymentGateway{

    private RazorpayClient razorPayClient;

    @Autowired
    private RazorPayPaymentGateway(RazorpayClient razorpayClient)
    {
        this.razorPayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(Long amount, String userName, String userEmail, String userPhoneNumber,Long orderId){
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",System.currentTimeMillis()/1000 + 100*60); //epoch time stamp
            paymentLinkRequest.put("reference_id",orderId.toString());
//        paymentLinkRequest.put("description","Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name",userPhoneNumber);
            customer.put("contact",userName);
            customer.put("email",userEmail);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
            paymentLinkRequest.put("callback_url","https://scaler.com/");
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorPayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url");
        }
        catch (RazorpayException exception)
        {
            System.out.println(exception.getMessage());
        }
        return "";

    }

    @Override
    public PaymentStatus getPaymentStatus(String paymentId) {

        Payment payment = null;
        try {
            payment = razorPayClient.payments.fetch(paymentId);
        }
        catch (RazorpayException ex){
            System.out.println(ex.getMessage());
        }

        assert payment != null;

        if(payment.get("status").equals("captured"))
        {
            return PaymentStatus.SUCCESS;
        }if(payment.get("status").equals("failed"))
        {
            return PaymentStatus.FAILURE;
        }if(payment.get("status").equals("created"))
        {
            return PaymentStatus.PENDING;
        }

        return null;

    }
}
