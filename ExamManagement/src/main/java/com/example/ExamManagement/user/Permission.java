package com.example.ExamManagement.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    //SET OF PERMISSIONS
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),

    ADMIN_CREATE("admin_create"),

    ADMIN_DELETE("admin_delete"),

    APPLICANT_READ("applicant:read"),
    APPLICANT_CREATE("applicant:create"),
    APPLICANT_UPDATE("applicant: update");


    private final String permission;


}
