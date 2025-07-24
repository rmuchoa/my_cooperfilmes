package com.brasiliwood.cooperfilmes.domain.movie.script.review;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScriptReviewService {

    private final MovieScriptService movieScriptService;

    public MovieScript recordReview(ScriptReview review) {
        MovieScript script = movieScriptService.findScriptById(review.getScriptId());
        MovieScript reviewed = new ScriptReviewApplier(review).applyOn(script);
        return movieScriptService.saveScript(reviewed);
    }

}
