package com.brasiliwood.cooperfilmes.domain.movie.script;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScript {

    private Integer id;
    private String text;
    private ClientContact contact;

    public static MovieScript of(String text, ClientContact domain) {
        return new MovieScript(null, text, domain);
    }
}