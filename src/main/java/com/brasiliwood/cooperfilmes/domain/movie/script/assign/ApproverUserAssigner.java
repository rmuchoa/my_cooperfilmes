package com.brasiliwood.cooperfilmes.domain.movie.script.assign;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.user.User;

public class ApproverUserAssigner {

    private final User approverUser;

    public ApproverUserAssigner(User approverUser) {
        this.approverUser = approverUser;
    }

    public MovieScript assignOnScript(MovieScript script) {
        if (script.isNotWaitingForApproval())
            throw new ForbiddenAssignException(script.getStatus(), MovieScript.MovieScriptStatus.IN_APPROVAL);

        return script.assignApprover(approverUser);
    }
}
