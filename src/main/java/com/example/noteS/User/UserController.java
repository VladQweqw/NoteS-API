package com.example.noteS.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("check")
    public String check() {
        return userService.check();
    }

    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestBody User user_data
    ) {
        return userService.register(user_data);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(
            @RequestBody UserLoginRequest credentials
    ) {
       return userService.login(credentials);
    }
}
