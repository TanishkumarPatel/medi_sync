package com.example.appointmentservice.service;

import appointment.events.AppointmentEvent;
import com.example.appointmentservice.dto.appointmentRequest;
import com.example.appointmentservice.dto.appointmentResponse;
import com.example.appointmentservice.exception.Patientnotfoundexception;
import com.example.appointmentservice.mapper.appointmentMapper;
import com.example.appointmentservice.repository.appointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.appointmentservice.model.appointment;


@Service
public class appointmentService {
    private static final Logger log = LoggerFactory.getLogger(appointmentService.class);
    private final appointmentRepository appointmentRepository;
    private final KafkaTemplate<String,byte[]> kafkaTemplate;
    private final RestTemplate restTemplate;

    public appointmentService(appointmentRepository appointmentRepository, KafkaTemplate<String,byte[]> kafkaTemplate) {
        this.appointmentRepository = appointmentRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = new RestTemplate();
    }
    public appointmentResponse bookAppointment(appointmentRequest request) {
        String url = "http://api-gateway:4004/api/patients/" + request.getPatientId();
        try {
            restTemplate.getForEntity(url, Object.class);
        } catch (Exception e) {
            throw new Patientnotfoundexception("Patient ID " + request.getPatientId() + " not found.");
        }
        appointment appointment = appointmentRepository.save(appointmentMapper.toModel(request));
        AppointmentEvent event= AppointmentEvent.newBuilder()
                .setAppointmentId(appointment.getId().toString())
                .setPatientId(appointment.getPatientId().toString())
                .setDoctorName(appointment.getDoctorName())
                .setAppointmentTime(appointment.getAppointmentTime().toString())
                .build();
        try{
            kafkaTemplate.send("appointment",event.toByteArray());
            log.info("✅ Sent BYTES for appointment: {}",appointment.getId());
        }
        catch (Exception ex){
            log.error("error sending patient event: {}",event);
        }
        return appointmentMapper.toDTO(appointment);
    }
}
