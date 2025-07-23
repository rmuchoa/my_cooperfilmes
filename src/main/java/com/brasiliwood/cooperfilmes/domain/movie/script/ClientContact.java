package com.brasiliwood.cooperfilmes.domain.movie.script;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientContact {
    private String name;
    private String email;
    private String phone;

    public static ClientContact of(String name, String email, String phone) {
        return new ClientContact(name, email, phone);
    }
}
