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

    public MovieScript submitScript(MovieScript movieScript) {
        MovieScriptEntity entity = repository.save(MovieScriptEntity.of(movieScript));
        return entity.toDomain();
    }

    public MovieScript findScriptById(Integer scriptId) {
        return repository.findById(scriptId)
                .map(MovieScriptEntity::toDomain)
                .orElseThrow(MovieScriptNotFoundException::new);
    }
}
