package com.brasiliwood.cooperfilmes.domain.movie.script.assign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAssign {

    private Integer scriptId;
    private String userEmail;

    public static UserAssign of(Integer scriptId, String userEmail) {
        return new UserAssign(scriptId, userEmail);
    }
}
