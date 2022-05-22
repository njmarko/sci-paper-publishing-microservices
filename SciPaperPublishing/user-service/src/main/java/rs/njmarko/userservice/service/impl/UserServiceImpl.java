package rs.njmarko.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.njmarko.userservice.model.LoginRequest;
import rs.njmarko.userservice.model.User;
import rs.njmarko.userservice.repository.UserRepository;
import rs.njmarko.userservice.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String register(User user) throws IllegalArgumentException {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username " + user.getUsername() + " is taken!");
        }
        user.setIsLoggedIn(Boolean.FALSE);
        userRepository.save(user);
        return "User successfully registered";
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        User found = user.orElseThrow(() -> new IllegalArgumentException("Username or password is not valid!"));

        if (!found.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Username or password is not valid!");
        }
        found.setIsLoggedIn(Boolean.TRUE);
        userRepository.save(found);
        return "User " + loginRequest.getUsername() + " successfully logged in!";
    }

    @Override
    public Boolean isLoggedIn(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        User found = user.orElseThrow(() -> new IllegalArgumentException("User with username " + username + " does not exist!"));
        return found.getIsLoggedIn();
    }

    @Override
    public String getFullName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        User found = user.orElseThrow(() -> new IllegalArgumentException("User with username " + username + " does not exist!"));
        return found.getFirstName() + " " + found.getLastName();
    }
}
