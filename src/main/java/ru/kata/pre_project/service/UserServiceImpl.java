package ru.kata.pre_project.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pre_project.dao.UserDao;
import ru.kata.pre_project.dto.UserDto;
import ru.kata.pre_project.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, ModelMapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userDao.getAll()
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(int id) {
        return mapper.map(userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(id))), UserDto.class);
    }

    @Override
    public void edit(UserDto userDto) {
        userDao.update(mapper.map(userDto, User.class));
    }

    @Override
    public void add(UserDto userDto) {
        userDao.save(mapper.map(userDto, User.class));
    }

    @Override
    public void deleteById(int id) {
        userDao.findById(id)
                .ifPresent(userDao::delete);
    }
}
