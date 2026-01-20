package com.partha.aws.aws_springboot_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CognitoAppClientService {

    private final CognitoIdentityProviderClient cognitoClient;

    public Map<String, String> createAppClient(String userPoolId, String clientName) {

        CreateUserPoolClientRequest request =
                CreateUserPoolClientRequest.builder()
                        .userPoolId(userPoolId)
                        .clientName(clientName)
                        .explicitAuthFlows(
                                ExplicitAuthFlowsType.ALLOW_USER_PASSWORD_AUTH,
                                ExplicitAuthFlowsType.ALLOW_REFRESH_TOKEN_AUTH
                        )
                        .generateSecret(true) // 🔑 ENABLE CLIENT SECRET
                        .build();

        CreateUserPoolClientResponse response =
                cognitoClient.createUserPoolClient(request);

        Map<String, String> result = new HashMap<>();
        result.put("clientId", response.userPoolClient().clientId());
        result.put("clientSecret", response.userPoolClient().clientSecret());

        return result;
    }
}
