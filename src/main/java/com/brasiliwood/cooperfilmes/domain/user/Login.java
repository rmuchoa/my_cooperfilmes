package com.brasiliwood.cooperfilmes.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private String email;
    private String password;

    public static Login of(String email, String password) {
        return new Login(email, password);
    }

}
