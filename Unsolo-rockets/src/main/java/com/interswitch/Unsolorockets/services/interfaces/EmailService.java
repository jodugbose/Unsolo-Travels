package com.interswitch.Unsolorockets.services.interfaces;

import java.io.IOException;

public interface EmailService {
    void sendMail(String receiverEmail, String subject, String emailBody) throws IOException;
}
