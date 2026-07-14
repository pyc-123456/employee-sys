package com.employeesys.dto;

import com.employeesys.entity.Department;
import com.employeesys.entity.Employee;

/**
 * DTO与实体间的转换工具类
 */
public final class DtoConverter {

    private DtoConverter() {}

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setGender(employee.getGender());
        dto.setAge(employee.getAge());
        dto.setPhone(employee.getPhone());
        dto.setPosition(employee.getPosition());
        dto.setHireDate(employee.getHireDate());
        dto.setSalary(employee.getSalary());
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
        }
        return dto;
    }

    public static DepartmentDTO toDepartmentDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setGender(dto.getGender());
        employee.setAge(dto.getAge());
        employee.setPhone(dto.getPhone());
        employee.setPosition(dto.getPosition());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    public static Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return department;
    }
}
