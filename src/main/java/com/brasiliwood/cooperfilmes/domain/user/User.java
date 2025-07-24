package com.brasiliwood.cooperfilmes.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private UserPosition position;

    public static User of(
            String name,
            String email,
            String password,
            UserPosition position) {
        return of(null, name, email, password, position);
    }

    public static User of(
            Integer id,
            String name,
            String email,
            String password,
            UserPosition position) {
        return new User(id, name, email, password, position);
    }

    public enum UserPosition {
        ANALYST,
        REVIEWER,
        APPROVER
    }

}
