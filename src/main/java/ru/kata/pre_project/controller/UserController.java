package ru.kata.pre_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.pre_project.dto.UserDto;
import ru.kata.pre_project.model.User;
import ru.kata.pre_project.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto()); // ?????
        return "new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") UserDto userDto) {
        userService.add(userDto);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(name = "id") int id,
                           Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/")
    public String update(@ModelAttribute("user") UserDto user,
                         @RequestParam(name = "id") int id) {
        userService.edit(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
