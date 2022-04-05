package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "allUsers";
    }

    @GetMapping(value = "user/new")
    public String newPerson (Model model) {
        model.addAttribute("person", new User());
        return "new";
    }
    @PostMapping("user")
    public String create (@ModelAttribute("person") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/new";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("person") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "allUsers";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }
}
