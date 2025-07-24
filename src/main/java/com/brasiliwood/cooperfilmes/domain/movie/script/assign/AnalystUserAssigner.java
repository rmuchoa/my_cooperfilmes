package com.brasiliwood.cooperfilmes.domain.movie.script.assign;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.user.User;

public class AnalystUserAssigner {

    private final User analystUser;

    public AnalystUserAssigner(User analystUser) {
        this.analystUser = analystUser;
    }

    public MovieScript assignOnScript(MovieScript script) {
        if (script.isNotWaitingForAnalysis())
            throw new ForbiddenAssignException(script.getStatus(), MovieScript.MovieScriptStatus.IN_ANALYSIS);

        return script.assignAnalyst(analystUser);
    }
}
