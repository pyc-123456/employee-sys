package com.employeesys.service.impl;

import com.employeesys.dto.DtoConverter;
import com.employeesys.dto.EmployeeDTO;
import com.employeesys.entity.Department;
import com.employeesys.entity.Employee;
import com.employeesys.exception.ResourceNotFoundException;
import com.employeesys.repository.DepartmentRepository;
import com.employeesys.repository.EmployeeRepository;
import com.employeesys.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAllWithDepartment();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByName(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("员工", id));
    }

    @Override
    @Transactional
    public Employee create(EmployeeDTO employeeDTO) {
        Employee employee = DtoConverter.toEntity(employeeDTO);
        if (employeeDTO.getDepartmentId() != null) {
            var department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("部门", employeeDTO.getDepartmentId()));
            employee.setDepartment(department);
        }

        Employee saved = employeeRepository.save(employee);
        log.info("新增员工: id={}, name={}", saved.getId(), saved.getName());
        return saved;
    }

    @Override
    @Transactional
    public Employee update(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("员工", id));

        employee.setName(employeeDTO.getName());
        employee.setGender(employeeDTO.getGender());
        employee.setAge(employeeDTO.getAge());
        employee.setPhone(employeeDTO.getPhone());
        employee.setPosition(employeeDTO.getPosition());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setSalary(employeeDTO.getSalary());

        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("部门", employeeDTO.getDepartmentId()));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        Employee saved = employeeRepository.save(employee);
        log.info("更新员工: id={}, name={}", saved.getId(), saved.getName());
        return saved;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("员工", id);
        }
        employeeRepository.deleteById(id);
        log.info("删除员工: id={}", id);
    }
}
