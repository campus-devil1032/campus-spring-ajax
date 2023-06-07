package com.campus.campusspringajax.controller;

import com.campus.campusspringajax.dao.UserDao;
import com.campus.campusspringajax.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    // API
    @PostMapping("/users")
    public String addUser(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userDao.addUser(user);
        return "redirect:/users";
    }

    // API
    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        User user = userDao.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            userDao.updateUser(user);
        }
        return "redirect:/users";
    }

    // API
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userDao.deleteUser(id);
        return "redirect:/users";
    }

    // Page
    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "users";
    }

    // Page
    @GetMapping("/users/edit/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userDao.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-user";
        } else {
            return "redirect:/users";
        }
    }

    // Page
    @GetMapping("/users/delete/{id}")
    public String deleteUserById(@PathVariable int id, Model model) {
        User user = userDao.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "delete-user";
        } else {
            return "redirect:/users";
        }
    }

}