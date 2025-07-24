package com.brasiliwood.cooperfilmes.domain.movie.script.assign;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.user.User;

public class ReviewerUserAssigner {

    private final User reviewerUser;

    public ReviewerUserAssigner(User reviewerUser) {
        this.reviewerUser = reviewerUser;
    }

    public MovieScript assignOnScript(MovieScript script) {
        if (script.isNotWaitingForReview())
            throw new ForbiddenAssignException(script.getStatus(), MovieScript.MovieScriptStatus.IN_REVIEW);

        return script.assignReviewer(reviewerUser);
    }
}
