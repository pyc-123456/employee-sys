package com.employeesys.controller;

import com.employeesys.dto.DtoConverter;
import com.employeesys.dto.DepartmentDTO;
import com.employeesys.entity.Department;
import com.employeesys.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/add")
    public String addDepartment(Model model) {
        model.addAttribute("departmentDTO", new DepartmentDTO());
        return "department-form";
    }

    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {
        DepartmentDTO departmentDTO = DtoConverter.toDepartmentDTO(departmentService.findById(id));
        model.addAttribute("departmentDTO", departmentDTO);
        return "department-form";
    }

    @PostMapping("/save")
    public String saveDepartment(@Valid DepartmentDTO departmentDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "department-form";
        }

        try {
            if (departmentDTO.getId() == null) {
                departmentService.create(departmentDTO);
            } else {
                departmentService.update(departmentDTO.getId(), departmentDTO);
            }
            return "redirect:/departments";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "department-form";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
