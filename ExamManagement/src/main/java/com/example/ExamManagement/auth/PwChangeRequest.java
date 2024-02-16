package com.example.ExamManagement.auth;


import lombok.*;


@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PwChangeRequest {
    String password;
    String newPw;
    String confirmationPw;

}
