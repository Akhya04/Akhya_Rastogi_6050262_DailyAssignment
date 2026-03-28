package com.cg.dto;
import com.cg.entity.Department;

public class EmpMobileSearchDto {
    private Long employeeId;
    private String employeeName;
    private Department department; // Contains complete dept details

    public EmpMobileSearchDto(Long employeeId, String employeeName, Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
    }
    public Long getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public Department getDepartment() { return department; }
}