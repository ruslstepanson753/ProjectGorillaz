package com.javarush.stepanov.service;

public interface Validable {
    boolean isExistLogin(String login);
    boolean loginOrPasswordIsEmpty(String login, String password);
}
