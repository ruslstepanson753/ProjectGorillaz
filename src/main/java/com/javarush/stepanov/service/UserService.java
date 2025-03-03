package com.javarush.stepanov.service;

import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.exception.AppException;
import com.javarush.stepanov.mapping.Dto;
import com.javarush.stepanov.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
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

    public void updateUser(UserTo userTo) {
        User user = dto.from(userTo);
        User userInDb = userRepository.get(userTo.getId());
        userInDb.setLogin(userTo.getLogin());
        userInDb.setPassword(userTo.getPassword());
        userRepository.update(userInDb);
    }

    public Collection<UserTo> getAll() {
        List<User> list = userRepository
                .getAll()
                .stream()
                .toList();
        List<UserTo> list2= list.stream().map(dto::from).toList();
        return list2;

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
        User user = dto.from(userTo);
        user.setLossCount(gameName);
        UserTo userToNew = dto.from(user);
        updateUser(userToNew);
    }

    public void addUserWin(UserTo userTo, String gameName) {
        User user = dto.from(userTo);
        user.setWinsCount(gameName);
        UserTo userToNew = dto.from(user);
        updateUser(userToNew);
    }

}
