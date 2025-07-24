package com.brasiliwood.cooperfilmes.domain.movie.script.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScriptReview {

    private Integer scriptId;
    private String mistakes;
    private String suggestions;

    public static ScriptReview of(Integer scriptId, String mistakes, String suggestions) {
        return new ScriptReview(scriptId, mistakes, suggestions);
    }
}
