package com.brasiliwood.cooperfilmes.domain.movie.script;

import com.brasiliwood.cooperfilmes.domain.movie.script.analysis.ScriptAnalysis;
import com.brasiliwood.cooperfilmes.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScript {

    private Integer id;
    private String text;
    private String analysisJustification;
    private MovieScriptStatus status;
    private User user;
    private ClientContact contact;

    public Integer getUserId() {
        return Optional.ofNullable(user)
                .map(User::getId)
                .orElse(null);
    }

    public boolean isNotWaitingForAnalysis() {
        return !isWaitingForAnalysis();
    }

    public boolean isNotWaitingForReview() {
        return !isWaitingForReview();
    }

    public boolean isNotWaitingForApproval() {
        return !isWaitingForApproval();
    }

    public boolean isNotInAnalysis() {
        return !isInAnalysis();
    }

    public boolean isWaitingForAnalysis() {
        return status == MovieScriptStatus.WAITING_FOR_ANALYSIS;
    }

    public boolean isWaitingForReview() {
        return status == MovieScriptStatus.WAITING_FOR_REVIEW;
    }

    public boolean isWaitingForApproval() {
        return status == MovieScriptStatus.WAITING_FOR_APPROVAL;
    }

    public boolean isInAnalysis() {
        return status == MovieScriptStatus.IN_ANALYSIS;
    }

    public MovieScript assignAnalyst(User analyst) {
        return assignUser(MovieScriptStatus.IN_ANALYSIS, analyst);
    }

    public MovieScript assignReviewer(User reviewer) {
        return assignUser(MovieScriptStatus.IN_REVIEW, reviewer);
    }

    public MovieScript assignApprover(User approver) {
        return assignUser(MovieScriptStatus.IN_APPROVAL, approver);
    }

    public MovieScript assignUser(MovieScriptStatus status, User user) {
        return MovieScript.of(id, text, status, user, contact);
    }

    public MovieScript applyAnalysis(ScriptAnalysis analysis) {
        return MovieScript.of(
                id,
                text,
                analysis.getAnalysisJustification(),
                analysis.getStatus().getDomain(),
                user,
                contact);
    }

    public static MovieScript of(String text, ClientContact domain) {
        return of(null, text, MovieScriptStatus.WAITING_FOR_ANALYSIS, domain);
    }

    public static MovieScript of(
            Integer id,
            String text,
            MovieScriptStatus status,
            ClientContact contact) {
        return of(id, text, status, null, contact);
    }

    public static MovieScript of(
            Integer id,
            String text,
            MovieScriptStatus status,
            User user,
            ClientContact contact) {
        return of(id, text, null, status, user, contact);
    }

    public static MovieScript of(
            Integer id,
            String text,
            String analysisJustification,
            MovieScriptStatus status,
            User user,
            ClientContact contact) {
        return new MovieScript(id, text, analysisJustification, status, user, contact);
    }

    public enum MovieScriptStatus {
        WAITING_FOR_ANALYSIS,
        WAITING_FOR_REVIEW,
        WAITING_FOR_APPROVAL,
        IN_ANALYSIS,
        IN_REVIEW,
        IN_APPROVAL,
        APPROVED,
        REFUSED
    }

}