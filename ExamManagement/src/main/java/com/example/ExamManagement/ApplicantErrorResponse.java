package com.example.ExamManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantErrorResponse {

    private String message;
    private int errorStatus;
    private long timestamp;

}
