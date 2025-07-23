package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScriptRequest {
    private String text;
    private ClientContactDTO contact;

    public MovieScript toDomain() {
        return MovieScript.of(text, contact.toDomain());
    }
}
