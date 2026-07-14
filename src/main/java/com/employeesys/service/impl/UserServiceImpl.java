package com.employeesys.service.impl;

import com.employeesys.dto.RegisterDTO;
import com.employeesys.entity.User;
import com.employeesys.exception.DuplicateResourceException;
import com.employeesys.repository.UserRepository;
import com.employeesys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new DuplicateResourceException("用户名已存在");
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new DuplicateResourceException("两次输入的密码不一致");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        User saved = userRepository.save(user);
        log.info("新用户注册成功: username={}", saved.getUsername());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
