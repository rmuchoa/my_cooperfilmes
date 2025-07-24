package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.user.Login;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String email;
    private String password;

    public Login toDomain() {
        return Login.of(email, password);
    }

}
