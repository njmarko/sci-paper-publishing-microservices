package rs.njmarko.userservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.njmarko.userservice.model.LoginRequest;
import rs.njmarko.userservice.model.User;
import rs.njmarko.userservice.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("register")
    public String register(@RequestBody User user) throws IllegalAccessException {
        return userService.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("is-logged-in/{username}")
    public Boolean isLoggedIn(@PathVariable String username) {
        return userService.isLoggedIn(username);
    }

    @GetMapping("get-full-name/{username}")
    public String getFullName(@PathVariable String username) {
        return userService.getFullName(username);
    }
}
