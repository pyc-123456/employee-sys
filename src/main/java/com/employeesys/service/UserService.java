package com.employeesys.service;

import com.employeesys.dto.LoginDTO;
import com.employeesys.dto.RegisterDTO;
import com.employeesys.entity.User;

public interface UserService {
    User register(RegisterDTO registerDTO);
    User login(LoginDTO loginDTO);
    User findByUsername(String username);
}