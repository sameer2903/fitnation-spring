package com.example.fitnation.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.fitnation.models.User;
import com.example.fitnation.exceptions.ResourceNotFoundException;
import com.example.fitnation.models.Plan;
import com.example.fitnation.repositories.UserRepository;
import com.example.fitnation.repositories.PlanRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email is already taken!");
        }
        return userRepository.save(user);
    }


    public User authenticateUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
            return existingUser;
        }
        return null;
    }

    public ResponseEntity<?> subscribeUserToPlan(Integer userId, Integer planId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        user.getPlans().add(plan);
        userRepository.save(user);
        return ResponseEntity.ok("User subscribed to plan successfully!");
    }
    
    public ResponseEntity<Set<Plan>> getUserPlans(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getPlans());
    }
}
