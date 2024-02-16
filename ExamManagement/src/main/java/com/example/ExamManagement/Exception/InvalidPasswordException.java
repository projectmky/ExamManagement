package com.example.ExamManagement.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidPasswordException extends Exception {
    private String message;


}
