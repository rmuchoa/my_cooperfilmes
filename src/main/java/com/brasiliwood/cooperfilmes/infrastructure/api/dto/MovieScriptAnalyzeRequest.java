package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.analysis.ScriptAnalysis;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScriptAnalyzeRequest {

    private String justification;
    private AnalysisStatus status;

    public ScriptAnalysis toDomain(Integer scriptId) {
        return ScriptAnalysis.of(scriptId, status, justification);
    }

    @Getter
    @AllArgsConstructor
    public enum AnalysisStatus {

        FIT(MovieScript.MovieScriptStatus.WAITING_FOR_REVIEW),
        REFUSED(MovieScript.MovieScriptStatus.REFUSED);

        private final MovieScript.MovieScriptStatus domain;

    }

}
