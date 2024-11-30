package com.java.project.authentication;


import com.java.project.security.jwt.JwtService;
import com.java.project.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.java.project.user.User;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Transactional
    public AuthenticationResponse signup(RegisterRequest request) {

        User user = new User(request.getEmail(),passwordEncoder.encode(request.getPassword()),"ROLE_regular");

        userRepository.save(user);


        var jwtToken = jwtService.generateToken(Map.of("status", "inactive"),user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email not found"));
        Map<String, Object> claims = new HashMap<>();

        var jwtToken = jwtService.generateToken(claims,user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
