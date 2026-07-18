package com.example.demo.StudentServer.Dto;

public record StudentResponseDTO<T>(boolean success, String message, T data) {
}
