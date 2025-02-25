package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

import static com.javarush.stepanov.constants.ConstantsCommon.USERSERVICE_EMPTY_LINE;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String login, String password) {
        User user = User.builder()
                .login(login)
                .password(password)
                .build();
        userRepository.create(user);
        return user;
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long id) {
        return Optional.ofNullable(userRepository.get(id));
    }

    public boolean loginOrPasswordIsIncorrect(String login, String password) {
        Collection<User> users = getAll();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return false;
            }
        }
        return true;
    }

    public boolean isExistLogin(String login) {
        Collection<User> users = getAll();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String login) {
        Collection<User> allUsers = getAll();
        for (User u : allUsers) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public boolean loginOrPasswordIsEmpty(String login, String password) {
        return ((login.equals(USERSERVICE_EMPTY_LINE)) || (password.equals(USERSERVICE_EMPTY_LINE)));
    }



}
