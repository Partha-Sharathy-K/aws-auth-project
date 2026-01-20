package com.partha.aws.aws_springboot_project.dto;
import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String name;
    private String temporaryPassword;
    private String group;
}


