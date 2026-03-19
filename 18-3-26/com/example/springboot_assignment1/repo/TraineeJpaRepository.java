package com.example.springboot_assignment1.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_assignment1.entities.Trainee;
@Repository
public interface TraineeJpaRepository extends JpaRepository<Trainee, Integer>{

}