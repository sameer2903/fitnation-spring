// com/example/fitnation/controllers/PlanController.java
package com.example.fitnation.controllers;

import com.example.fitnation.models.Plan;
import com.example.fitnation.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@CrossOrigin(origins = "http://localhost:4200")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public Plan getPlanById(@PathVariable int id) {
        return planService.getPlanById(id);
    }

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planService.savePlan(plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable int id) {
        planService.deletePlan(id);
    }
}
