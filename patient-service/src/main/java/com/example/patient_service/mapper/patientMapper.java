package com.example.patient_service.mapper;

import com.example.patient_service.dto.PatientRequestDTO;
import com.example.patient_service.dto.PatientResponseDTO;
import com.example.patient_service.model.patient;

import java.time.LocalDate;

public class patientMapper {
    public static PatientResponseDTO toDTO(patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    public static patient toModel(PatientRequestDTO patientRequestDTO) {
        patient patient = new patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate())) ;
        return patient;
    }
}
