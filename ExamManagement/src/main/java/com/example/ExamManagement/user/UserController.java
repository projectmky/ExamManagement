package com.example.ExamManagement.user;

import com.example.ExamManagement.auth.PwChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;


    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody PwChangeRequest pwChangeRequest, Principal user) {
        userservice.changePw(pwChangeRequest, user);
        return ResponseEntity.accepted().build();

    }
}
