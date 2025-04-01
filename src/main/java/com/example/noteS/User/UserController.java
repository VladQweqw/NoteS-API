package com.example.noteS.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@RequestMapping("api/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String check() {
        return userService.check();
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(
            @RequestBody User user_data
    ) {
        return userService.createUser(user_data);
    }
}
