package com.example.notificationservice.email;

import org.springframework.stereotype.Component;

@Component
public class email_sender {
    public void sendEmail(String patientId, String messageBody) {
        System.out.println("\n======================================");
        System.out.println("EMAIL SENT TO PATIENT: " + patientId);
        System.out.println("CONTENT: " + messageBody);
        System.out.println("======================================\n");
    }
}
