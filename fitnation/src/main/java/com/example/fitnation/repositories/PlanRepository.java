// com/example/fitnation/repositories/PlanRepository.java
package com.example.fitnation.repositories;

import com.example.fitnation.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
