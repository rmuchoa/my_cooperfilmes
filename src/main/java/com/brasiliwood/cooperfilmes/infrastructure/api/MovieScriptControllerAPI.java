package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie/script")
public class MovieScriptControllerAPI {

    private final MovieScriptService service;

    public MovieScriptControllerAPI(MovieScriptService service) {
        this.service = service;
    }

    @PostMapping
    public void submitScript(@RequestBody MovieScriptRequest request) {
        service.submitScript(request.toDomain());
    }

}
