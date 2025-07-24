package com.brasiliwood.cooperfilmes.domain.movie.script;

import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptRepository;
import com.brasiliwood.cooperfilmes.infrastructure.repository.UserEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieScriptService {

    private final MovieScriptRepository repository;
    private final UserRepository userRepository;

    public MovieScriptService(
            MovieScriptRepository repository,
            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public MovieScript saveScript(MovieScript movieScript) {
        MovieScriptEntity entity = repository.save(MovieScriptEntity.of(movieScript));
        return entity.toDomain(movieScript::getUser);
    }

    public MovieScript findScriptById(Integer scriptId) {
        return repository.findById(scriptId)
                .map(entity ->
                        entity.toDomain(() ->
                                Optional.ofNullable(entity.getUserId())
                                        .flatMap(userRepository::findById)
                                        .map(UserEntity::toDomain)
                                        .orElse(null))
                )
                .orElseThrow(MovieScriptNotFoundException::new);
    }
}
