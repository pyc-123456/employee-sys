package com.employeesys.service;

import com.employeesys.dto.DepartmentDTO;
import com.employeesys.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    Department create(DepartmentDTO departmentDTO);
    Department update(Long id, DepartmentDTO departmentDTO);
    void delete(Long id);
}