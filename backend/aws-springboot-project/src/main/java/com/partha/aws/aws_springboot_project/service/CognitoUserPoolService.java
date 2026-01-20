package com.partha.aws.aws_springboot_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@Service
@RequiredArgsConstructor
public class CognitoUserPoolService {

    private final CognitoIdentityProviderClient cognitoClient;

    public String createUserPool(String poolName) {

        CreateUserPoolRequest request = CreateUserPoolRequest.builder()
                .poolName(poolName)

                // Login using EMAIL
                .usernameAttributes(UsernameAttributeType.EMAIL)
                .autoVerifiedAttributes(VerifiedAttributeType.EMAIL)

                // 🚫 MFA FULLY DISABLED
                .mfaConfiguration(UserPoolMfaType.OFF)

                // Password policy
                .policies(UserPoolPolicyType.builder()
                        .passwordPolicy(PasswordPolicyType.builder()
                                .minimumLength(8)
                                .requireUppercase(true)
                                .requireLowercase(true)
                                .requireNumbers(true)
                                .build())
                        .build())

                .build();

        CreateUserPoolResponse response =
                cognitoClient.createUserPool(request);

        return response.userPool().id();
    }
}