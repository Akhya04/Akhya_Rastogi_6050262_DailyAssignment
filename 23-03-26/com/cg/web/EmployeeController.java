package com.cg.web;

import com.cg.dto.EmpMobileSearchDto;
import com.cg.entity.Employee;
import com.cg.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/department/{deptName}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String deptName) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentName(deptName));
    }

    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<EmpMobileSearchDto> getEmployeeByMobile(@PathVariable String mobileNumber) {
        return ResponseEntity.ok(employeeService.getDetailsByMobile(mobileNumber));
    }
}