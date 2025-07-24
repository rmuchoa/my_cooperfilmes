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

    public User createUser(User user) {
        UserEntity entity = repository.save(UserEntity.of(user));
        return entity.toDomain();
    }

}
