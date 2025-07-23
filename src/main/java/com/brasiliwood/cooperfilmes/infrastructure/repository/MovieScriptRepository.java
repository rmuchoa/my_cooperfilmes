package com.brasiliwood.cooperfilmes.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieScriptRepository
        extends JpaRepository<MovieScriptEntity, Integer> {
}
