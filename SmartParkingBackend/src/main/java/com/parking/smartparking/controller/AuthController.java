package com.parking.smartparking.controller;

import com.parking.smartparking.model.User;
import com.parking.smartparking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userRepo.save(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User u = userRepo.findByEmail(user.getEmail());
        if (u != null && u.getPassword().equals(user.getPassword())) {
            return u;
        }
        return null;
    }
}