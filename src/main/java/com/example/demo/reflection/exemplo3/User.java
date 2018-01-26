package com.example.demo.reflection.exemplo3;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String name;

    private String email;

    private String pass;

    public boolean validateEmail() {
        return email.contains("@");
    }

    public boolean validatePass() {
        return pass.length() > 8;
    }
}
