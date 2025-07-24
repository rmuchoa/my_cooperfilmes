package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.user.User;
import com.brasiliwood.cooperfilmes.domain.user.UserService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.UserCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerAPI {

    private final UserService service;

    public UserControllerAPI(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@RequestBody UserCreateRequest request) {
        return service.createUser(request.toDomain());
    }

}
