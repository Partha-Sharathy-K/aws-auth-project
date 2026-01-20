package com.partha.aws.aws_springboot_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest;

@Service
@RequiredArgsConstructor
public class CognitoGroupService {

    private final CognitoIdentityProviderClient cognitoClient;

    public void addUserToGroup(String userPoolId, String username, String groupName) {

        AdminAddUserToGroupRequest request =
                AdminAddUserToGroupRequest.builder()
                        .userPoolId(userPoolId)
                        .username(username)
                        .groupName(groupName)
                        .build();

        cognitoClient.adminAddUserToGroup(request);
    }
}