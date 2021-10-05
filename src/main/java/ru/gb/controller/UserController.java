package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/userList")
    public String userList(Model model) {
        model.addAttribute("allUsers", repository.findAll());
        return "userListAdmin";
    }

    @GetMapping("/superAdmin")
    public String userListAll (Model model) {
        model.addAttribute("findAllUsers", repository.findAll());
        return "superAdmin";
    }


    @PostMapping("/userList")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            repository.deleteById(userId);
        }
        return "redirect:/userList";
    }

    @GetMapping("/edit/{id}")
    public String updateUser (@PathVariable long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("updateUser", user);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String update(@RequestParam(required = true, defaultValue = "" ) long userId,
                         @RequestParam(required = true, defaultValue = "" ) String action,
                         Model model) {
        if (action.equals("updateUser")){
            repository.update(userId);
        }
        return "redirect:/updateUser";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @PathVariable User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            repository.save(user);
            return "registration";
        }


        return "redirect:/";
    }
}
