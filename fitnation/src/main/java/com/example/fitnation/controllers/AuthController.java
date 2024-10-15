package com.example.fitnation.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.fitnation.models.Plan;
import com.example.fitnation.models.User;
import com.example.fitnation.services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user);
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed!");
        }
    }



    @PostMapping("/{userId}/subscribe/{planId}")
    public ResponseEntity<Map<String, String>> subscribeUserToPlan(@PathVariable Integer userId, @PathVariable Integer planId) {
        userService.subscribeUserToPlan(userId, planId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User subscribed to plan successfully!");
        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/{userId}/plans")
    public ResponseEntity<Set<Plan>> getUserPlans(@PathVariable Integer userId) {
        return userService.getUserPlans(userId);
    }
}
