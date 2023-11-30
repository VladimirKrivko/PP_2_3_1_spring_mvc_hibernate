package ru.kata.pre_project.dto;

import java.util.Objects;

public class UserDto {
    private int id;
    private String firstName;
    private String secondName;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public UserDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public UserDto setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return getId() == userDto.getId() && getFirstName().equals(userDto.getFirstName()) && Objects.equals(getSecondName(), userDto.getSecondName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSecondName());
    }

    @Override
    public String toString() {
        return "UserDto{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", secondName='" + secondName + '\'' +
               '}';
    }
}
