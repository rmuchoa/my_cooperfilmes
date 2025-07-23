package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie/script")
public class MovieScriptControllerAPI {

    private final MovieScriptService service;

    public MovieScriptControllerAPI(MovieScriptService service) {
        this.service = service;
    }

    @PostMapping
    public MovieScript submitScript(@RequestBody MovieScriptRequest request) {
        return service.submitScript(request.toDomain());
    }

}
