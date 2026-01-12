package com.example.patient_service.exception;

public class Patientnotfoundexception extends RuntimeException {
    public Patientnotfoundexception(String message) {
        super(message);
    }
}
