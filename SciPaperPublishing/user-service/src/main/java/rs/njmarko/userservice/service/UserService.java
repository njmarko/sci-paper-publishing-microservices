package rs.njmarko.userservice.service;

import rs.njmarko.userservice.model.LoginRequest;
import rs.njmarko.userservice.model.User;

public interface UserService {
    String register(User user) throws IllegalAccessException;

    String login(LoginRequest loginRequest);

    Boolean isLoggedIn(String username);

    String getFullName(String username);
}
