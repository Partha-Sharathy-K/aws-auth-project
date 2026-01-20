package com.partha.aws.aws_springboot_project.dto;
import lombok.Data;

@Data
public class SetPasswordRequest {
    private String email;
    private String newPassword;
}
