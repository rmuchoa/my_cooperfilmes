package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.user.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String name;
    private String email;
    private UserPosition position;

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                UserPosition.of(user.getPosition()));
    }

    @Getter
    @AllArgsConstructor
    public enum UserPosition {

        ANALYST(User.UserPosition.ANALYST),
        REVIEWER(User.UserPosition.REVIEWER),
        APPROVER(User.UserPosition.APPROVER);

        private final User.UserPosition domain;

        private static UserPosition of(User.UserPosition position) {
            for (UserPosition type : values())
                if (type.domain == position)
                    return type;
            return null;
        }

    }

}
