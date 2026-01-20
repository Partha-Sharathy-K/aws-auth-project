package com.partha.aws.aws_springboot_project.dto;
import lombok.Data;

@Data
public class NewPasswordRequest {

    private String email;
    private String newPassword;
    private String sessionToken;
}