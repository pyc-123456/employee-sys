package com.employeesys.service;

import com.employeesys.dto.RegisterDTO;
import com.employeesys.entity.User;

public interface UserService {
    User register(RegisterDTO registerDTO);
    User findByUsername(String username);
}
