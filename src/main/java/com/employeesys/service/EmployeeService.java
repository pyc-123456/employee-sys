package com.employeesys.service;

import com.employeesys.dto.EmployeeDTO;
import com.employeesys.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    List<Employee> findByName(String name);
    Employee findById(Long id);
    Employee create(EmployeeDTO employeeDTO);
    Employee update(Long id, EmployeeDTO employeeDTO);
    void delete(Long id);
}