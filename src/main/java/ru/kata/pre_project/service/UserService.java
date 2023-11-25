package ru.kata.pre_project.service;

import ru.kata.pre_project.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(int id);

    User edit(int id, String firstName, String secondName);

    User add(String firstName, String secondName);

    void deleteById(int id);
}
