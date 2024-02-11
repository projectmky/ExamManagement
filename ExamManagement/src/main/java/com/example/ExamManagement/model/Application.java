package com.example.ExamManagement.model;

import jakarta.persistence.OneToOne;

import java.util.Calendar;

public class Application {

    @OneToOne
    private Applicant Applicant;
    private Calendar signedDate;

    private String imgSrc;


}
