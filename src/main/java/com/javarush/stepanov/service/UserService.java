package com.javarush.stepanov.service;

import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.mapping.Dto;
import com.javarush.stepanov.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

import static com.javarush.stepanov.constants.ConstantsCommon.EMPTY_LINE;

@AllArgsConstructor
@Transactional
public class UserService implements Validable, Autorizationable {
    private final Dto dto;
    private final UserRepository userRepository;

    public UserTo createUser(String login, String password) {
        User loginPattern = User.builder().login(login).password(password).build();
            userRepository.create(loginPattern);
        return dto.from(loginPattern);
    }

    public Collection<UserTo> getAll() {
        return userRepository
                .getAll()
                .stream()
                .map(dto::from)
                .toList();
    }


    public Optional<UserTo> get(long id) {
        return Optional
                .ofNullable(userRepository.get(id))
                .map(dto::from);
    }

    public Optional<UserTo> get(String login, String password) {
        User patternUser = User
                .builder()
                .login(login)
                .password(password)
                .build();
        return userRepository
                .find(patternUser)
                .map(dto::from)
                .findAny();
    }

    public UserTo findUser(String login) {
        Collection<UserTo> allUsersTo = getAll();
        for (UserTo u : allUsersTo) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean loginOrPasswordIsEmpty(String login, String password) {
        return ((login.equals(EMPTY_LINE)) || (password.equals(EMPTY_LINE)));
    }

    @Override
    public boolean loginOrPasswordIsIncorrect(String login, String password) {
        Collection<UserTo> users = getAll();
        for (com.javarush.stepanov.dto.UserTo userTo : users) {
            if (userTo.getLogin().equals(login) && userTo.getPassword().equals(password)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isExistLogin(String login) {
        Collection<UserTo> usersTo = getAll();
        for (com.javarush.stepanov.dto.UserTo userTo : usersTo) {
            if (userTo.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void addUserLoss(UserTo userTo, String gameName) {
        Long id = userTo.getId();
        User user = userRepository.get(id);
        user.inkrLossCount(gameName);
    }

    public void addUserWin(UserTo userTo, String gameName) {
        Long id = userTo.getId();
        User user = userRepository.get(id);
        user.inkrWinsCount(gameName);
    }

    public UserTo getActualUserTo(UserTo userTo) {
        if (userTo != null){
            Long id = userTo.getId();
            return get(id).orElse(null);
        }
        return null;
    }
}
