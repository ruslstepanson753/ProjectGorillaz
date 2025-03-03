package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.UserTo;
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

    public com.javarush.stepanov.dto.UserTo createUser(String login, String password) {
        UserTo user = UserTo.builder()
                .login(login)
                .password(password)
                .build();
        userRepository.create(user);
        return dto.from(user);
    }

    public Collection<com.javarush.stepanov.dto.UserTo> getAll() {
        return userRepository
                .getAll()
                .stream()
                .map(dto::from)
                .toList();
    }

    public Optional<com.javarush.stepanov.dto.UserTo> get(long id) {
        return Optional
                .ofNullable(userRepository.get(id))
                .map(dto::from);
    }

    public Optional<com.javarush.stepanov.dto.UserTo> get(String login, String password) {
        UserTo patternUser = UserTo
                .builder()
                .login(login)
                .password(password)
                .build();
        return userRepository
                .find(patternUser)
                .findAny()
                .map(dto::from);
    }

    public com.javarush.stepanov.dto.UserTo findUser(String login) {
        Collection<com.javarush.stepanov.dto.UserTo> allUsersTo = getAll();
        for (com.javarush.stepanov.dto.UserTo u : allUsersTo) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public void updateUser(com.javarush.stepanov.dto.UserTo userTo) {
        UserTo user = dto.from(userTo);
        userRepository.update(user);
    }

    @Override
    public boolean loginOrPasswordIsEmpty(String login, String password) {
        return ((login.equals(EMPTY_LINE)) || (password.equals(EMPTY_LINE)));
    }

    @Override
    public boolean loginOrPasswordIsIncorrect(String login, String password) {
        Collection<com.javarush.stepanov.dto.UserTo> users = getAll();
        for (com.javarush.stepanov.dto.UserTo userTo : users) {
            if (userTo.getLogin().equals(login) && userTo.getPassword().equals(password)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isExistLogin(String login) {
        Collection<com.javarush.stepanov.dto.UserTo> usersTo = getAll();
        for (com.javarush.stepanov.dto.UserTo userTo : usersTo) {
            if (userTo.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void addUserLoss(com.javarush.stepanov.dto.UserTo userTo, String gameName) {
        UserTo user = dto.from(userTo);
        user.setLossCount(gameName);
        com.javarush.stepanov.dto.UserTo userToNew = dto.from(user);
        updateUser(userToNew);
    }

    public void addUserWin(com.javarush.stepanov.dto.UserTo userTo, String gameName) {
        UserTo user = dto.from(userTo);
        user.setWinsCount(gameName);
        com.javarush.stepanov.dto.UserTo userToNew = dto.from(user);
        updateUser(userToNew);
    }

}
