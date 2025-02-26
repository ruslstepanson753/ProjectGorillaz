package com.javarush.stepanov.service;

public interface Autorizationable {
    boolean loginOrPasswordIsIncorrect(String login, String password);
}
