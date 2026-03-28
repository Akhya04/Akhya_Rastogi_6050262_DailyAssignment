package com.cg.dto;

public class DeptCountDto {
    private String departmentName;
    private Long employeeCount;

    public DeptCountDto(String departmentName, Long employeeCount) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
    }
    public String getDepartmentName() { return departmentName; }
    public Long getEmployeeCount() { return employeeCount; }
}