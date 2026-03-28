package com.cg.repo;

import com.cg.entity.Department;
import com.cg.dto.DeptCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    
    // Operation 3: DTO mapping for group by
    @Query("SELECT new com.cg.dto.DeptCountDto(d.name, COUNT(e)) FROM Department d LEFT JOIN d.employees e GROUP BY d.name")
    List<DeptCountDto> countEmployeesPerDepartment();
}