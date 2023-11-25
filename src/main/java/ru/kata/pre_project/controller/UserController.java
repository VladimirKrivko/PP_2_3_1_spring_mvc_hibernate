package ru.kata.pre_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.pre_project.model.User;
import ru.kata.pre_project.service.UserService;

import java.util.List;

@Controller
//@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") User user) {
        userService.add(user.getFirstName(), user.getSecondName());
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(Model model,
                           @RequestParam(name = "id") int id) {

        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PostMapping("/")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(name = "id") int id) {
        userService.edit(id, user.getFirstName(), user.getSecondName());
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
