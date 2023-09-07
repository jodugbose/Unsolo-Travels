package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class EmailMailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailMailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;


    @Override
    public void sendMail(String receiverEmail, String subject, String emailBody) throws IOException {
        try {
            LOGGER.info("**** Beginning of log ****");
            LOGGER.info("Sending mail to: " + receiverEmail);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(receiverEmail);
            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setText(emailBody);

            javaMailSender.send(message);

            LOGGER.info("Email sent to: " + receiverEmail);
            LOGGER.info("**** End of log ****");

            new ResponseEntity<>("Email sent", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error sending email to " + receiverEmail, e);
            new ResponseEntity<>("An error occurred while sending the email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
