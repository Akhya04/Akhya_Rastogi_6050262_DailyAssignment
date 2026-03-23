package com.cg.repo;

import com.cg.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = {"department"})
    List<Employee> findAll();

    List<Employee> findByDepartmentName(String departmentName);

    @Query("SELECT e FROM Employee e JOIN e.mobileNumbers m WHERE m = :mobile")
    Optional<Employee> findByMobileNumber(@Param("mobile") String mobileNumber);
}