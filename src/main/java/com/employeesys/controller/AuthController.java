package com.employeesys.controller;

import com.employeesys.dto.LoginDTO;
import com.employeesys.dto.RegisterDTO;
import com.employeesys.entity.User;
import com.employeesys.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.register(registerDTO);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

}
