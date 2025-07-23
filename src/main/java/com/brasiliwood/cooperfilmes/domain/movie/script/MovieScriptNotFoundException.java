package com.brasiliwood.cooperfilmes.domain.movie.script;

public class MovieScriptNotFoundException extends RuntimeException {

    public MovieScriptNotFoundException() {
        super("Desired movie script was not found by given scriptId");
    }

}
