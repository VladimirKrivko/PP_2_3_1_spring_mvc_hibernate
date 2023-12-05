package ru.kata.pre_project.service;

import ru.kata.pre_project.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto findById(int id);

    void edit(UserDto userDto);

    void add(UserDto userDto);

    void deleteById(int id);
}
