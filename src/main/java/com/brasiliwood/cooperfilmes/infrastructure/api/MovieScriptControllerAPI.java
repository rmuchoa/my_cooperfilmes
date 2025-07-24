package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptRequest;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie/script")
public class MovieScriptControllerAPI {

    private final MovieScriptService service;

    public MovieScriptControllerAPI(MovieScriptService service) {
        this.service = service;
    }

    @PostMapping
    public MovieScriptResponse submitScript(@RequestBody MovieScriptRequest request) {
        MovieScript script = service.saveScript(request.toDomain());
        return MovieScriptResponse.of(script);
    }

    @GetMapping("/{scriptId}")
    public MovieScriptResponse checkScriptSubmission(@PathVariable("scriptId") Integer scriptId) {
        MovieScript script = service.findScriptById(scriptId);
        return MovieScriptResponse.of(script);
    }

}
