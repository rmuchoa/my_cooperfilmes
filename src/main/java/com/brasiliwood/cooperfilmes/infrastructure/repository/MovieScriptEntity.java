package com.brasiliwood.cooperfilmes.infrastructure.repository;

import static jakarta.persistence.EnumType.STRING;

import com.brasiliwood.cooperfilmes.domain.movie.script.ClientContact;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "movie_script")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieScriptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "client_email", unique = true)
    private String clientEmail;

    @Enumerated(value = STRING)
    private MovieScriptStatus status;

    public static MovieScriptEntity of(MovieScript movieScript) {
        return new MovieScriptEntity(
                movieScript.getId(),
                movieScript.getText(),
                movieScript.getContact().getName(),
                movieScript.getContact().getPhone(),
                movieScript.getContact().getEmail(),
                MovieScriptStatus.AGUARDANDO_ANALISE);
    }

    public MovieScript toDomain() {
        return MovieScript.of(
                id,
                text,
                status.getDomain(),
                ClientContact.of(
                        clientName,
                        clientPhone,
                        clientEmail
                ));
    }

    @Getter
    @AllArgsConstructor
    enum MovieScriptStatus {
        AGUARDANDO_ANALISE(MovieScript.MovieScriptStatus.AGUARDANDO_ANALISE),
        EM_ANALISE(MovieScript.MovieScriptStatus.EM_ANALISE),
        AGUARDANDO_REVISAO(MovieScript.MovieScriptStatus.AGUARDANDO_REVISAO),
        EM_REVISAO(MovieScript.MovieScriptStatus.EM_REVISAO),
        AGUARDANDO_APROVACAO(MovieScript.MovieScriptStatus.AGUARDANDO_APROVACAO),
        EM_APROVACAO(MovieScript.MovieScriptStatus.EM_APROVACAO),
        APROVADO(MovieScript.MovieScriptStatus.APROVADO),
        RECUSADO(MovieScript.MovieScriptStatus.RECUSADO);

        private final MovieScript.MovieScriptStatus domain;

    }

}
