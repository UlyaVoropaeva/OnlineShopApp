package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("user/{id}")
    public User getById(@PathVariable Long id) {
        return repository.getById(id);
    }


    @GetMapping("/user")
    public String userList(Model model) {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        model.addAttribute("users", repository.findAll());
        return "user";
    }

    @DeleteMapping ("/user")
    public String  delete(@RequestParam(required = true, defaultValue = "" ) long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            repository.deleteById(userId);
        }
        return "redirect:/user";
    }

    @GetMapping("/user-update/{id}")
    public String updateUser (@PathVariable long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("updateUser", user);
        return "user";
    }

    @PostMapping("/user-update")
    public String update(@RequestParam(required = true, defaultValue = "" ) long userId,
                         @RequestParam(required = true, defaultValue = "" ) String action,
                         Model model) {
        if (action.equals("updateUser")){
            repository.update(userId);
        }
        return "redirect:/user-update";
    }

    @GetMapping("/user-add")
    public String edit (Model model) {
        model.addAttribute("userForm", new User());
        return "user-add";
    }

    @PostMapping("/user-add")
    public String addUser(@ModelAttribute("userForm") @PathVariable User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "editUser";
        }
        repository.save(user);
        return "redirect:/";
    }
}
