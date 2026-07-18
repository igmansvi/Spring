package com.example.demo.StudentServer.Dto;

public record StudentRequestDTO (
    String name,
    int age,
    String department
) {}
