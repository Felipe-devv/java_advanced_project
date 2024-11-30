package com.java.project.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {


    private String email;

    private String password;

    public RegisterRequest() {
    }


}
