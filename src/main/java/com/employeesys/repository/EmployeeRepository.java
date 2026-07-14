package com.employeesys.repository;

import com.employeesys.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = {"department"})
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllWithDepartment();

    @EntityGraph(attributePaths = {"department"})
    List<Employee> findByNameContaining(String name);

    @EntityGraph(attributePaths = {"department"})
    List<Employee> findByDepartmentId(Long departmentId);
}
