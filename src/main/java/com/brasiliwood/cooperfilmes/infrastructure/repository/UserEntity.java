package com.brasiliwood.cooperfilmes.infrastructure.repository;

import com.brasiliwood.cooperfilmes.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "approval_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;

    @Enumerated(value = STRING)
    @Column(name = "user_position")
    private UserPosition userPosition;

    public User toDomain() {
        return User.of(
                id,
                name,
                email,
                password,
                userPosition.domain);
    }

    public static UserEntity of(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                UserEntity.UserPosition.of(user.getPosition()));
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
