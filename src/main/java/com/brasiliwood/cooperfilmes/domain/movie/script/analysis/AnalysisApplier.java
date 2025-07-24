package com.brasiliwood.cooperfilmes.domain.movie.script.analysis;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.ForbiddenAssignException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnalysisApplier {

    private ScriptAnalysis analysis;

    public MovieScript applyOn(MovieScript script) {
        if (script.isNotInAnalysis())
            throw new ForbiddenAssignException(script.getStatus(), MovieScript.MovieScriptStatus.WAITING_FOR_REVIEW);

        return script.applyAnalysis(analysis);
    }

}
