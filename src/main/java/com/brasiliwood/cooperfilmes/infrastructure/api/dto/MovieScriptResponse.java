package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScriptResponse {

    private Integer id;
    private String text;
    private String analysisJustification;
    private String status;
    private UserResponse user;
    private ClientContactDTO contact;

    public static MovieScriptResponse of(MovieScript script) {
        return MovieScriptResponse.builder()
                .id(script.getId())
                .text(script.getText())
                .analysisJustification(script.getAnalysisJustification())
                .status(script.getStatus().name())
                .user(UserResponse.of(script.getUser()))
                .contact(ClientContactDTO.of(script.getContact()))
                .build();
    }
}
