package com.brasiliwood.cooperfilmes.domain.movie.script.review;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.ForbiddenAssignException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScriptReviewApplier {

    private ScriptReview review;

    public MovieScript applyOn(MovieScript script) {
        if (script.isNotInReview())
            throw new ForbiddenAssignException(script.getStatus(), MovieScript.MovieScriptStatus.WAITING_FOR_APPROVAL);

        return script.applyReview(review);
    }

}
