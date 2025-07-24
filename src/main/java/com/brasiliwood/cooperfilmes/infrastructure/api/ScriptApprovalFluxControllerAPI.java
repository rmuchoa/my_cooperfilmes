package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.analysis.ScriptAnalysisService;
import com.brasiliwood.cooperfilmes.domain.movie.script.review.ScriptReviewService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptAnalyzeRequest;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptResponse;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/movie/script")
public class ScriptApprovalFluxControllerAPI {

    private final ScriptAnalysisService analysisService;
    private final ScriptReviewService reviewService;

    @PostMapping("{scriptId}/analysis")
    public MovieScriptResponse analyseScript(
            @PathVariable("scriptId") Integer scriptId,
            @RequestBody MovieScriptAnalyzeRequest request) {

        MovieScript script = analysisService.recordAnalysis(request.toDomain(scriptId));
        return MovieScriptResponse.of(script);
    }

    @PostMapping("{scriptId}/review")
    public MovieScriptResponse reviewScript(
            @PathVariable("scriptId") Integer scriptId,
            @RequestBody MovieScriptReviewRequest request) {

        MovieScript script = reviewService.recordReview(request.toDomain(scriptId));
        return MovieScriptResponse.of(script);
    }

}
