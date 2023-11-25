package ru.kata.pre_project.dao;

import ru.kata.pre_project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAll();

    Optional<User> getById(int id);

    void saveOrUpdate(User user);

    void delete(User user);
}
