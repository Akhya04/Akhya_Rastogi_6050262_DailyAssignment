package com.cg.repo;

import com.cg.entity.Employee;
import com.cg.dto.EmpMobileSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    // Operation 2: Fetch all with dept and manager (JOIN FETCH prevents N+1)
    @Query("SELECT e FROM Employee e JOIN FETCH e.department")
    List<Employee> fetchAllWithDepartments();

    // Operation 4: Fetch by dept name using JOIN FETCH
    @Query("SELECT e FROM Employee e JOIN FETCH e.department d WHERE d.name = :deptName")
    List<Employee> findByDepartmentNameWithFetch(@Param("deptName") String deptName);

    // Operation 5: Custom JPQL injecting directly into the DTO constructor
    @Query("SELECT new com.cg.dto.EmpMobileSearchDto(e.id, e.name, e.department) FROM Employee e JOIN e.mobileNumbers m WHERE m = :mobile")
    Optional<EmpMobileSearchDto> fetchDetailsByMobileNumber(@Param("mobile") String mobile);
}