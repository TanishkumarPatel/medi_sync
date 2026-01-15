package com.example.appointmentservice.repository;

import com.example.appointmentservice.model.appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface appointmentRepository extends JpaRepository<appointment, UUID> {

}
