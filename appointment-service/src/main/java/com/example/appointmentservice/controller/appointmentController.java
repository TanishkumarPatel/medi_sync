package com.example.appointmentservice.controller;

import com.example.appointmentservice.dto.appointmentRequest;
import com.example.appointmentservice.dto.appointmentResponse;
import com.example.appointmentservice.service.appointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class appointmentController {
    private final appointmentService service;
    public appointmentController(appointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<appointmentResponse> createAppointment(@RequestBody appointmentRequest request) {
        appointmentResponse response = service.bookAppointment(request);
        return ResponseEntity.ok().body(response);
    }
}
