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
        User user = findUserByEmail(login.getEmail());
        if (user.matchPassword(login.getPassword()))
            return user;
        throw new UnauthorizedLoginException();
    }

    public User findUserByEmail(String userEmail) {
        return repository.findByEmail(userEmail)
                .map(UserEntity::toDomain)
                .orElseThrow(UserNotFoundException::new);
    }
}
