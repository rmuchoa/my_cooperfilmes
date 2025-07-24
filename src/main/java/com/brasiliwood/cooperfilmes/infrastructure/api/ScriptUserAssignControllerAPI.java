package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.UserAssign;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.UserAssignService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie/script")
public class ScriptUserAssignControllerAPI {

    private final UserAssignService service;

    public ScriptUserAssignControllerAPI(UserAssignService service) {
        this.service = service;
    }

    @PostMapping("{scriptId}/assign/{userEmail}")
    public MovieScriptResponse analyseScript(
            @PathVariable("scriptId") Integer scriptId,
            @PathVariable("userEmail") String userEmail) {

        MovieScript script = service.assignScriptToUser(UserAssign.of(scriptId, userEmail));
        return MovieScriptResponse.of(script);
    }

}
