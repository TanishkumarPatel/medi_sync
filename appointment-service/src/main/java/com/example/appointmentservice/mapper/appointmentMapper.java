package com.example.appointmentservice.mapper;

import com.example.appointmentservice.dto.appointmentRequest;
import com.example.appointmentservice.dto.appointmentResponse;
import com.example.appointmentservice.model.appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public class appointmentMapper {
    public static appointmentResponse toDTO(appointment appointment) {
        appointmentResponse response = new appointmentResponse();
        response.setId(appointment.getId());
        response.setPatientId(appointment.getPatientId().toString());
        response.setDoctorName(appointment.getDoctorName());
        response.setAppointmentTime(appointment.getAppointmentTime().toString());
        return response;
    }

    public static appointment toModel(appointmentRequest request) {
        appointment appointment = new appointment();
        appointment.setPatientId(UUID.fromString(request.getPatientId()));
        appointment.setDoctorName(request.getDoctorName());
        appointment.setAppointmentTime(LocalDateTime.parse(request.getAppointmentTime()));
        return appointment;
    }
}
