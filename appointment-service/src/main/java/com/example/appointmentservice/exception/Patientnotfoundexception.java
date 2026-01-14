package com.example.appointmentservice.exception;

public class Patientnotfoundexception extends RuntimeException {
    public Patientnotfoundexception(String message) {
        super(message);
    }
}
