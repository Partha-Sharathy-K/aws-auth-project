package com.partha.aws.aws_springboot_project.dto;

import lombok.Data;

@Data
public class RevokeTokenRequestDto {
    private String refreshToken;
}

