package com.example.notificationservice.kafka;

import appointment.events.AppointmentEvent;
import com.example.notificationservice.email.email_sender;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class notificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(notificationConsumer.class);
    private final email_sender emailSender;
    public notificationConsumer(email_sender emailSender) {
        this.emailSender = emailSender;
    }
    @KafkaListener(topics="appointment",groupId = "notification-service")
    public void consume(byte[] message){
        try {
             AppointmentEvent event = AppointmentEvent.parseFrom(message);
            String text = "Your appointment with Dr. " + event.getDoctorName() +
                    " at " + event.getAppointmentTime() + " is confirmed.";

            emailSender.sendEmail(event.getPatientId(), text);
        }
        catch (InvalidProtocolBufferException e)
        {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }
}
