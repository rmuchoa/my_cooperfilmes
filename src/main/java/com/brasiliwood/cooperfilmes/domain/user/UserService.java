package com.brasiliwood.cooperfilmes.domain.user;

import com.brasiliwood.cooperfilmes.infrastructure.repository.UserEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User loginUser(Login login) {
        return repository.findByEmail(login.getEmail())
                .map(UserEntity::toDomain)
                .orElseThrow(UserNotFoundException::new);
    }

}
