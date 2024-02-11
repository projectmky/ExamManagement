package com.example.ExamManagement.model;

import lombok.Getter;

@Getter
public enum Level {
    BEGINNER(1),
    ADVANCED(2);

    private final int value;

    Level(int value) {
        this.value = value;
    }
}
