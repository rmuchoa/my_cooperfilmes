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
    private MovieScriptStatus status;
    private ClientContact contact;

    public static MovieScript of(String text, ClientContact domain) {
        return of(null, text, MovieScriptStatus.AGUARDANDO_ANALISE, domain);
    }

    public static MovieScript of(
            Integer id,
            String text,
            MovieScriptStatus status,
            ClientContact domain) {
        return new MovieScript(id, text, status, domain);
    }

    public enum MovieScriptStatus {
        AGUARDANDO_ANALISE,
        EM_ANALISE,
        AGUARDANDO_REVISAO,
        EM_REVISAO,
        AGUARDANDO_APROVACAO,
        EM_APROVACAO,
        APROVADO,
        RECUSADO
    }

}