package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.analysis.ScriptAnalysisService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptAnalyzeRequest;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/movie/script")
public class ScriptAnalysisControllerAPI {

    private final ScriptAnalysisService service;

    @PostMapping("{scriptId}/analysis")
    public MovieScriptResponse analyseScript(
            @PathVariable("scriptId") Integer scriptId,
            @RequestBody MovieScriptAnalyzeRequest request) {

        MovieScript script = service.recordAnalysis(request.toDomain(scriptId));
        return MovieScriptResponse.of(script);
    }

}
