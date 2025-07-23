package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.movie.script.ClientContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientContactDTO {

    private String name;
    private String phone;
    private String email;

    public ClientContact toDomain() {
        return ClientContact.of(name, phone, email);
    }

    public static ClientContactDTO of(ClientContact contact) {
        return ClientContactDTO.builder()
                .name(contact.getName())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .build();
    }

}
