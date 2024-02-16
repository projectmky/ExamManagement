package com.example.ExamManagement.user;

//import com.example.ExamManagement.Exception.ConfirmationPwNotEqualException;
//import com.example.ExamManagement.Exception.InvalidPasswordException;

import com.example.ExamManagement.auth.PwChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void changePw(PwChangeRequest request, Principal connectedUser) throws ResponseStatusException {

        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());

        //Check if the password entered by user is the correct password as stored in system

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            // throw new InvalidPasswordException("The password entered is not valid! Enter again");
            //  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unauthorized. The password entered is not valid!");
            throw new IllegalStateException("Wrong pw");

        // Check if the newPW is equal to the confirmationPw
        if (!request.getNewPw().equals(request.getConfirmationPw()))
            //  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The Confirmation Password does not match the new password. Please re-enter");
            throw new IllegalStateException("Pw does not match");

        user.setPassword(passwordEncoder.encode(request.getNewPw()));
        userRepository.save(user);
    }
}
