package com.partha.aws.aws_springboot_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CognitoProvisioningService {

    private final CognitoUserService userService;
    private final CognitoGroupService groupService;

    public void createUserAndAssignGroup(
            String userPoolId,
            String username,
            String email,
            String groupName
    ) {
        userService.createUser(userPoolId, username, email);
        groupService.addUserToGroup(userPoolId, username, groupName);
    }
}
