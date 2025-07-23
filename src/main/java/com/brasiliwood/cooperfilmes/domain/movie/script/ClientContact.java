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
    private String phone;
    private String email;

    public static ClientContact of(String name, String phone, String email) {
        return new ClientContact(name, phone, email);
    }

}
