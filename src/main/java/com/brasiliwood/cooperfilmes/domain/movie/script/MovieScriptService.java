package com.brasiliwood.cooperfilmes.domain.movie.script;

import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieScriptService {

    private final MovieScriptRepository repository;

    public MovieScriptService(MovieScriptRepository repository) {
        this.repository = repository;
    }

    public void submitScript(MovieScript movieScript) {
        repository.save(MovieScriptEntity.of(movieScript));
    }

}
