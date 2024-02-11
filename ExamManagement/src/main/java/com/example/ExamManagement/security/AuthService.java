package com.example.ExamManagement.security;

import com.example.ExamManagement.repository.UserRepository;
import com.example.ExamManagement.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        System.out.println("user: " + user.getUsername());
        System.out.println("user: " + user.getPassword());
        System.out.println("jwt token generated " + jwtToken);
        LOG.debug("Register, token generation for '{}'", user.getUsername());
        LOG.debug("Token generated '{}'", jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        LOG.debug("Authenticated Token '{}'", jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse changePw(AuthenticationRequest request) {
        authenticate(request);
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        userRepo.save(user);

        return AuthenticationResponse.builder().build();
    }
}
