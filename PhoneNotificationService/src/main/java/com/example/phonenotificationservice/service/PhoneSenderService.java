package com.example.phonenotificationservice.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PhoneSenderService {
    @Value("${twilio.account_sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth_token}")
    private String AUTH_TOKEN;

    @Value("${twilio.number}")
    private String twilioPhoneNumber;

    public void sendMessage(String userPhone, String orderNumber) {

        String text = "Dear Customer,\n\nYour order with order number " + orderNumber + " has been created.";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber(userPhone),
                        new PhoneNumber(twilioPhoneNumber),
                        text)
                .create();
        log.info("message sent");


    }

}

