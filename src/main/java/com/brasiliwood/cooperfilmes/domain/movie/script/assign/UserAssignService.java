package com.brasiliwood.cooperfilmes.domain.movie.script.assign;

import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import com.brasiliwood.cooperfilmes.domain.user.User;
import com.brasiliwood.cooperfilmes.domain.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserAssignService {

    private final MovieScriptService movieScriptService;
    private final UserService userService;

    public UserAssignService(
            MovieScriptService movieScriptService,
            UserService userService) {
        this.movieScriptService = movieScriptService;
        this.userService = userService;
    }

    public MovieScript assignScriptToUser(UserAssign userAssign) {
        MovieScript script = movieScriptService.findScriptById(userAssign.getScriptId());
        User user = userService.findUserByEmail(userAssign.getUserEmail());
        MovieScript assignedScript = user.assignOnScript(script);
        return movieScriptService.saveScript(assignedScript);
    }

}
