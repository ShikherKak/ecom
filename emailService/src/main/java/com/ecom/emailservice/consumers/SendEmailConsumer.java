package com.ecom.emailservice.consumers;

import com.ecom.emailservice.dtos.SendEmailMessageDto;
import com.ecom.emailservice.utils.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
public class SendEmailConsumer {

    private final ObjectMapper objectMapper;
    private SendEmailConsumer(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    //used this resourse to use make kafka work on windows:
    //had to move kafka to a path where there are no spaces in the path
    //had to delete previous logs to make kafka work properly
    //https://stackoverflow.com/questions/25037263/apache-zookeeper-error-on-windows-couldnot-find-or-load-main-class-quorumpeerm
    @KafkaListener(topics = "sendEmail",id="handleEmailConsumerGroup")
    public void handleEmailSend(String messageDto) throws JsonProcessingException {
        SendEmailMessageDto sendEmailMessageDto = objectMapper.readValue(messageDto, SendEmailMessageDto.class);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            private final String password = System.getenv("SMTP_PASSWORD");
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendEmailMessageDto.getFrom(), password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session,
                sendEmailMessageDto.getTo(),
                "Signed Up Successfully!!",
                "You have successfully signed up!!");

    }


}
