package com.brasiliwood.cooperfilmes.domain.movie.script.analysis;

import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptAnalyzeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScriptAnalysis {

    private Integer scriptId;
    private MovieScriptAnalyzeRequest.AnalysisStatus status;
    private String justification;

    public static ScriptAnalysis of(
            Integer scriptId,
            MovieScriptAnalyzeRequest.AnalysisStatus status,
            String justification) {
        return new ScriptAnalysis(scriptId, status, justification);
    }
}
