package com.employeesys.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DuplicateResourceException.class)
    public String handleDuplicate(DuplicateResourceException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuth(AuthenticationException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(BusinessException.class)
    public String handleBusiness(BusinessException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        log.error("系统运行时异常", ex);
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        log.error("系统内部错误", ex);
        model.addAttribute("error", "系统内部错误，请稍后重试");
        return "error";
    }
}
