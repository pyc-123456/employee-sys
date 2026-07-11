package com.employeesys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50个字符")
    private String name;

    @Size(max = 10, message = "性别长度不能超过10个字符")
    private String gender;

    @Positive(message = "年龄必须为正数")
    private Integer age;

    @Size(max = 20, message = "电话长度不能超过20个字符")
    private String phone;

    @Size(max = 50, message = "职位长度不能超过50个字符")
    private String position;

    private LocalDate hireDate;

    @Positive(message = "薪水必须为正数")
    private BigDecimal salary;

    private Long departmentId;
}