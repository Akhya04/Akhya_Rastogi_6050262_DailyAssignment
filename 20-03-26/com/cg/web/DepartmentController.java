package com.cg.web;

import com.cg.entity.Department;
import com.cg.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentRepo.save(department), HttpStatus.CREATED);
    }

    @GetMapping("/employee-counts")
    public ResponseEntity<List<Object[]>> getEmployeeCounts() {
        return ResponseEntity.ok(departmentRepo.countEmployeesPerDepartment());
    }
}