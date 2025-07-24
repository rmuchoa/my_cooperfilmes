package com.brasiliwood.cooperfilmes.domain.movie.script.analysis;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScriptAnalysisService {

    private final MovieScriptService movieScriptService;

    public MovieScript recordAnalysis(ScriptAnalysis analysis) {
        MovieScript script = movieScriptService.findScriptById(analysis.getScriptId());
        MovieScript analysisApplied = new AnalysisApplier(analysis).applyOn(script);
        return movieScriptService.saveScript(analysisApplied);
    }

}
