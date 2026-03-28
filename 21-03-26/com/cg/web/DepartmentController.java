package com.cg.web;

import com.cg.dto.DeptCountDto;
import com.cg.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee-counts")
    public ResponseEntity<List<DeptCountDto>> getEmployeeCounts() {
        return ResponseEntity.ok(employeeService.getEmployeeCountPerDepartment());
    }
}