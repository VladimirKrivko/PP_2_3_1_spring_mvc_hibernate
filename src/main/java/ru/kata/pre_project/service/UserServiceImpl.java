package ru.kata.pre_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pre_project.dao.UserDao;
import ru.kata.pre_project.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(int id) {
        Optional<User> user = userDao.getById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("user with id = %d not found".formatted(id));
    }

    @Override
    public int getTotalCount() {
        return userDao.getTotalCount();
    }

    @Override
    @Transactional
    public User edit(int id, String firstName, String secondName) {
        Optional<User> user = userDao.getById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setFirstName(firstName);
            updatedUser.setSecondName(secondName);
            userDao.saveOrUpdate(updatedUser);
            return updatedUser;
        }
        throw new RuntimeException("user with id = %d not found".formatted(id));
    }

    @Override
    public User add(String firstName, String secondName) {
        User savedUser = new User();
        savedUser.setFirstName(firstName);
        savedUser.setSecondName(secondName);
        userDao.saveOrUpdate(savedUser);
        return savedUser;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<User> user = userDao.getById(id);
        user.ifPresent(userDao::delete);
    }
}
