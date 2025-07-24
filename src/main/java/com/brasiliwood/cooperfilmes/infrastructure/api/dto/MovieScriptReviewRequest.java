package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.movie.script.review.ScriptReview;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScriptReviewRequest {

    private String mistakes;
    private String suggestions;

    public ScriptReview toDomain(Integer scriptId) {
        return ScriptReview.of(scriptId, mistakes, suggestions);
    }

}
