package com.employeesys.config;

import com.employeesys.dto.DepartmentDTO;
import com.employeesys.dto.RegisterDTO;
import com.employeesys.service.DepartmentService;
import com.employeesys.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final DepartmentService departmentService;

    public DataInitializer(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) {
        if (userService.findByUsername("admin") == null) {
            RegisterDTO admin = new RegisterDTO();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setConfirmPassword("admin123");
            admin.setEmail("admin@example.com");
            userService.register(admin);
        }

        if (departmentService.findAll().isEmpty()) {
            DepartmentDTO dto1 = new DepartmentDTO();
            dto1.setName("技术部");
            dto1.setDescription("负责公司技术研发");
            departmentService.create(dto1);

            DepartmentDTO dto2 = new DepartmentDTO();
            dto2.setName("人力资源部");
            dto2.setDescription("负责员工招聘和管理");
            departmentService.create(dto2);

            DepartmentDTO dto3 = new DepartmentDTO();
            dto3.setName("财务部");
            dto3.setDescription("负责公司财务管理");
            departmentService.create(dto3);

            DepartmentDTO dto4 = new DepartmentDTO();
            dto4.setName("市场部");
            dto4.setDescription("负责市场推广和销售");
            departmentService.create(dto4);
        }
    }
}