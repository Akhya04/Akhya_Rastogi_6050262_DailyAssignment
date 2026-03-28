package com.cg.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Must be Long to match JpaRepository<Employee, Long>

    // @NotBlank is safer than @NotEmpty for Strings as it prevents "   " (blank spaces)
    @NotBlank(message = "Employee name must not be empty")
    private String name;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private Double salary; // Wrapper class used so @NotNull can catch missing data

    @ElementCollection
    @CollectionTable(name = "employee_mobiles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "mobile_number")
    @NotEmpty(message = "You must provide at least one mobile number")
    private Set<@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String> mobileNumbers = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    @NotNull(message = "Department must not be null")
    @JsonIgnoreProperties("employees") // Matches the setup in your Department class
    private Department department;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
    
    public Set<String> getMobileNumbers() { return mobileNumbers; }
    public void setMobileNumbers(Set<String> mobileNumbers) { this.mobileNumbers = mobileNumbers; }
    
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}