package com.example.demo.StudentServer.Dto;

public record StudentResponse<T>(boolean success, String message, T data) {
}
