package ru.kata.pre_project.service;

import ru.kata.pre_project.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User findById(int id);

    User edit(User user);

    void add(User user);

    void deleteById(int id);
}
