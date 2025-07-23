package com.brasiliwood.cooperfilmes.infrastructure.repository;

import static jakarta.persistence.EnumType.STRING;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "movie_script")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieScriptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Lob
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

    enum MovieScriptStatus {
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
