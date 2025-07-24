package com.brasiliwood.cooperfilmes.domain.user;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.AnalystUserAssigner;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.ApproverUserAssigner;
import com.brasiliwood.cooperfilmes.domain.movie.script.assign.ReviewerUserAssigner;
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

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public MovieScript assignOnScript(MovieScript script) {
        return switch (position) {
            case ANALYST -> buildAnalystAssigner().assignOnScript(script);
            case REVIEWER -> buildReviewerAssigner().assignOnScript(script);
            case APPROVER -> buildApproverAssigner().assignOnScript(script);
        };
    }

    private AnalystUserAssigner buildAnalystAssigner() {
        return new AnalystUserAssigner(this);
    }

    private ReviewerUserAssigner buildReviewerAssigner() {
        return new ReviewerUserAssigner(this);
    }

    private ApproverUserAssigner buildApproverAssigner() {
        return new ApproverUserAssigner(this);
    }

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
