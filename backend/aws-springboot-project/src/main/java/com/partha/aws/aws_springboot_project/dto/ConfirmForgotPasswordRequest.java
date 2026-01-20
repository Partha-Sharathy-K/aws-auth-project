package com.partha.aws.aws_springboot_project.dto;
import lombok.Data;

@Data
public class ConfirmForgotPasswordRequest {
    private String email;
    private String otp;
    private String newPassword;
}

