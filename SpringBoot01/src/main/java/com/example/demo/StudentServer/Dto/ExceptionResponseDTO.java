package com.example.demo.StudentServer.Dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(LocalDateTime timestamp, int statusCode, String statusMessage, String errorMessage, String path) {
}
