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
import java.util.Optional;

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
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(user -> mapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto findById(int id) {
        return mapper.map(userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(id))), UserDto.class);
    }

    @Override
    public void edit(UserDto userDto) {
        userDao.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("userDto with id = %d not found".formatted(userDto.getId())))
                .setFirstName(userDto.getFirstName())
                .setSecondName(userDto.getSecondName());
    }

    @Override
    public void add(UserDto userDto) {
        userDao.save(mapper.map(userDto, User.class));
    }

    @Override
    public void deleteById(int id) {
        Optional<User> user = userDao.findById(id);
        user.ifPresent(userDao::delete);
    }
}
