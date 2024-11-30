package com.java.project.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {


    private String token;

    public AuthenticationResponse() {
    }
}
