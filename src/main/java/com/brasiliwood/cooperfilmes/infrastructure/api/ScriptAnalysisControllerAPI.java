package com.brasiliwood.cooperfilmes.domain.movie.script.analysis;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie/script")
public class ScriptAnalysisControllerAPI {

    @PostMapping("{scriptId}/analysis")
    public void analyseScript(
            @PathVariable("scriptId") Integer scriptId,
            @RequestBody AnalyzeScriptRequest request) {

        MovieScript script = 
        return MovieScriptResponse.of(script);
    }

}
