package com.partha.aws.aws_springboot_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@Service
@RequiredArgsConstructor
public class CognitoUserService {

    private final CognitoIdentityProviderClient cognitoClient;

    public void createUser(String userPoolId, String username, String email) {

        AdminCreateUserRequest request =
                AdminCreateUserRequest.builder()
                        .userPoolId(userPoolId)
                        .username(username)
                        .userAttributes(
                                AttributeType.builder()
                                        .name("email")
                                        .value(email)
                                        .build(),
                                AttributeType.builder()
                                        .name("email_verified")
                                        .value("true")
                                        .build()
                        )
                        .messageAction(MessageActionType.SUPPRESS) // no email for now
                        .build();

        cognitoClient.adminCreateUser(request);
    }
}
