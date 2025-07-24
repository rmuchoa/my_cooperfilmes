package com.brasiliwood.cooperfilmes.infrastructure.repository;

import static jakarta.persistence.EnumType.STRING;

import com.brasiliwood.cooperfilmes.domain.movie.script.ClientContact;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movie_script")
public class MovieScriptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(columnDefinition = "TEXT", name = "analysis_justification")
    private String analysisJustification;

    @Column(columnDefinition = "TEXT", name = "review_mistakes")
    private String reviewMistakes;

    @Column(columnDefinition = "TEXT", name = "review_suggestions")
    private String reviewSuggestions;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "client_email", unique = true)
    private String clientEmail;

    @Enumerated(value = STRING)
    private MovieScriptStatus status;

    private Integer userId;

    public static MovieScriptEntity of(MovieScript movieScript) {
        return new MovieScriptEntity(
                movieScript.getId(),
                movieScript.getText(),
                movieScript.getAnalysisJustification(),
                movieScript.getReviewPointedMistakes(),
                movieScript.getReviewSuggestions(),
                movieScript.getContact().getName(),
                movieScript.getContact().getPhone(),
                movieScript.getContact().getEmail(),
                MovieScriptStatus.of(movieScript.getStatus()),
                movieScript.getUserId());
    }

    public MovieScript toDomain(Supplier<User> userSupplier) {
        return MovieScript.of(
                id,
                text,
                analysisJustification,
                reviewMistakes,
                reviewSuggestions,
                status.getDomain(),
                userSupplier.get(),
                ClientContact.of(
                        clientName,
                        clientPhone,
                        clientEmail
                ));
    }

    @Getter
    @AllArgsConstructor
    public enum MovieScriptStatus {

        AGUARDANDO_ANALISE(MovieScript.MovieScriptStatus.WAITING_FOR_ANALYSIS),
        AGUARDANDO_REVISAO(MovieScript.MovieScriptStatus.WAITING_FOR_REVIEW),
        AGUARDANDO_APROVACAO(MovieScript.MovieScriptStatus.WAITING_FOR_APPROVAL),
        EM_ANALISE(MovieScript.MovieScriptStatus.IN_ANALYSIS),
        EM_REVISAO(MovieScript.MovieScriptStatus.IN_REVIEW),
        EM_APROVACAO(MovieScript.MovieScriptStatus.IN_APPROVAL),
        APROVADO(MovieScript.MovieScriptStatus.APPROVED),
        RECUSADO(MovieScript.MovieScriptStatus.REFUSED);

        private final MovieScript.MovieScriptStatus domain;

        private static MovieScriptStatus of(MovieScript.MovieScriptStatus domain) {
            for (MovieScriptStatus value : values())
                if (value.domain == domain)
                    return value;
            return null;
        }

    }

}
