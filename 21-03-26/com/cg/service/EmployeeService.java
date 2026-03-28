package com.cg.service;

import com.cg.dto.DeptCountDto;
import com.cg.dto.EmpMobileSearchDto;
import com.cg.entity.Department;
import com.cg.entity.Employee;
import com.cg.exception.DepartmentNameNotFoundException;
import com.cg.exception.MobileNumberDoesNotExistsForEmployeeException;
import com.cg.repo.DepartmentRepo;
import com.cg.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    // Operation 1
    public Employee addEmployee(Employee emp) {
        if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
            Department dept = departmentRepo.findById(emp.getDepartment().getId())
                    .orElseThrow(() -> new DepartmentNameNotFoundException("Department ID not found"));
            emp.setDepartment(dept);
        }
        return employeeRepo.save(emp);
    }

    // Operation 2
    public List<Employee> getAllEmployees() {
        return employeeRepo.fetchAllWithDepartments();
    }

    // Operation 3
    public List<DeptCountDto> getEmployeeCountPerDepartment() {
        return departmentRepo.countEmployeesPerDepartment();
    }

    // Operation 4
    public List<Employee> getEmployeesByDepartmentName(String deptName) {
        List<Employee> list = employeeRepo.findByDepartmentNameWithFetch(deptName);
        if (list.isEmpty()) {
            throw new DepartmentNameNotFoundException("No employees found under department: " + deptName);
        }
        return list;
    }

    // Operation 5
    public EmpMobileSearchDto getDetailsByMobile(String mobile) {
        return employeeRepo.fetchDetailsByMobileNumber(mobile)
                .orElseThrow(() -> new MobileNumberDoesNotExistsForEmployeeException("Mobile number " + mobile + " does not exist for any employee."));
    }
}