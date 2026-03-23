package com.cg.web;

import com.cg.entity.Employee;
import com.cg.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepo.findAll());
    }

    @GetMapping("/department/{deptName}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String deptName) {
        List<Employee> employees = employeeRepo.findByDepartmentName(deptName);
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<Employee> getEmployeeByMobile(@PathVariable String mobileNumber) {
        Optional<Employee> employee = employeeRepo.findByMobileNumber(mobileNumber);
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
}