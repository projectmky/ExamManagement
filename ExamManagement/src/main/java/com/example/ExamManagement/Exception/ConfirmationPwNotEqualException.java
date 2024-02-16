package com.example.ExamManagement.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmationPwNotEqualException extends Exception {
    private String message;

}
