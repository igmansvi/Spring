package com.example.demo.StudentServer;

public record StudentResponse(boolean success, String message, Student data) {
}
