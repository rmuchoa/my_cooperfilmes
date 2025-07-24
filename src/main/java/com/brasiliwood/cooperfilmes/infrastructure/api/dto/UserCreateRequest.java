package com.brasiliwood.cooperfilmes.infrastructure.api.dto;

import com.brasiliwood.cooperfilmes.domain.user.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String name;
    private String email;
    private String password;
    private UserPosition position;

    public User toDomain() {
        return User.of(name, email, password, position.domain);
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
