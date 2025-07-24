package com.brasiliwood.cooperfilmes.domain.movie.script.assign;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;

import static java.lang.String.format;

public class ForbiddenAssignException extends RuntimeException {

    public ForbiddenAssignException(
            MovieScript.MovieScriptStatus from,
            MovieScript.MovieScriptStatus to) {
        super(format("Forbidden to transition between %s status and %s status!", from, to));
    }

}
