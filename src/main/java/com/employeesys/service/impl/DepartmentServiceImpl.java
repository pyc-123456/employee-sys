package com.employeesys.service.impl;

import com.employeesys.dto.DepartmentDTO;
import com.employeesys.dto.DtoConverter;
import com.employeesys.entity.Department;
import com.employeesys.exception.ResourceNotFoundException;
import com.employeesys.repository.DepartmentRepository;
import com.employeesys.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("部门", id));
    }

    @Override
    @Transactional
    public Department create(DepartmentDTO departmentDTO) {
        Department department = DtoConverter.toEntity(departmentDTO);
        Department saved = departmentRepository.save(department);
        log.info("新增部门: id={}, name={}", saved.getId(), saved.getName());
        return saved;
    }

    @Override
    @Transactional
    public Department update(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("部门", id));
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        Department saved = departmentRepository.save(department);
        log.info("更新部门: id={}, name={}", saved.getId(), saved.getName());
        return saved;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("部门", id);
        }
        departmentRepository.deleteById(id);
        log.info("删除部门: id={}", id);
    }
}
