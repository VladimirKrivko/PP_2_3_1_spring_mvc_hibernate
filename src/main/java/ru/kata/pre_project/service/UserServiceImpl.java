package ru.kata.pre_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pre_project.dao.UserDao;
import ru.kata.pre_project.model.User;

import javax.persistence.EntityNotFoundException;
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
    public User findById(int id) {
        return userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(id)));
    }

    @Override
    public User edit(User user) {
        return userDao.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(user.getId())))
                .setFirstName(user.getFirstName())
                .setSecondName(user.getSecondName());
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(int id) {
        Optional<User> user = userDao.findById(id);
        user.ifPresent(userDao::delete);
    }
}
