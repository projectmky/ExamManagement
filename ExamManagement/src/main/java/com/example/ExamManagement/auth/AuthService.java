package com.example.ExamManagement.auth;

import com.example.ExamManagement.Exception.ApplicantNotFoundException;
import com.example.ExamManagement.token.Token;
import com.example.ExamManagement.token.TokenRepository;
import com.example.ExamManagement.token.TokenType;
import com.example.ExamManagement.user.User;
import com.example.ExamManagement.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder().user(user).tokenType(TokenType.BEARER).token(jwtToken).isExpired(false).isRevoked(false).build();
        tokenRepository.save(token);
    }

    private void revokeAllTokensOfUser(User user) {
        var allValidTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (allValidTokens.isEmpty())
            return;
        allValidTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        System.out.println("user: " + user.getUsername());
        System.out.println("jwt token generated " + jwtToken);
        System.out.println("Refresh token generated " + refreshToken);

        LOG.debug("Register, token generation for '{}'", user.getUsername());
        LOG.debug("Token generated '{}'", refreshToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        //access token
        var jwtToken = jwtService.generateToken(user);
        //refresh token
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllTokensOfUser(user);
        saveUserToken(user, jwtToken);
        LOG.debug("Access Token '{}'", jwtToken);
        LOG.debug("Refresh Token '{}'", refreshToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }


    public void refreshToken(HttpServletResponse response, HttpServletRequest request) throws ApplicantNotFoundException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String username;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authorizationHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            //find user

            var userDetails = this.userRepo.findByUsername(username);

            //validate token if user is found
            if (userDetails.isPresent() && jwtService.isTokenValid(refreshToken, userDetails.get())) {
                var accessToken = jwtService.generateToken(userDetails.get());
                var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }
}
