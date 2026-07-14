package com.employeesys.controller;

import com.employeesys.dto.DtoConverter;
import com.employeesys.dto.EmployeeDTO;
import com.employeesys.entity.Employee;
import com.employeesys.service.DepartmentService;
import com.employeesys.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listEmployees(@RequestParam(required = false) String name, Model model) {
        List<Employee> employees;
        if (name != null && !name.isEmpty()) {
            employees = employeeService.findByName(name);
        } else {
            employees = employeeService.findAll();
        }
        model.addAttribute("employees", employees);
        model.addAttribute("searchName", name);
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        model.addAttribute("departments", departmentService.findAll());
        return "employee-form";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id);
        EmployeeDTO employeeDTO = DtoConverter.toEmployeeDTO(employee);
        model.addAttribute("employeeDTO", employeeDTO);
        model.addAttribute("departments", departmentService.findAll());
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "employee-form";
        }

        try {
            if (employeeDTO.getId() == null) {
                employeeService.create(employeeDTO);
            } else {
                employeeService.update(employeeDTO.getId(), employeeDTO);
            }
            return "redirect:/employees";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("departments", departmentService.findAll());
            return "employee-form";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
