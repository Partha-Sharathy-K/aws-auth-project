package com.partha.aws.aws_springboot_project.controller;

import com.partha.aws.aws_springboot_project.service.CognitoAppClientService;
import com.partha.aws.aws_springboot_project.service.CognitoUserPoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/cognito")
@RequiredArgsConstructor
public class CognitoAdminController {

    private final CognitoUserPoolService userPoolService;
    private final CognitoAppClientService appClientService;

    /**
     * Create ONE user pool + MANY app clients (with secrets)
     */
    @PostMapping("/create-user-pool-with-clients")
    public Map<String, Object> createUserPoolWithClients(
            @RequestParam String poolName,
            @RequestBody List<String> clientNames) {

        String userPoolId = userPoolService.createUserPool(poolName);

        List<Map<String, String>> clients = new ArrayList<>();

        for (String clientName : clientNames) {
            Map<String, String> client =
                    appClientService.createAppClient(userPoolId, clientName);

            client.put("clientName", clientName);
            clients.add(client);
        }
        return Map.of(
                "userPoolId", userPoolId,
                "clients", clients
        );
    }
    @PostMapping("/add-client")
    public Map<String, String> addClientToExistingPool(
            @RequestParam String userPoolId,
            @RequestParam String clientName) {

        return appClientService.createAppClient(userPoolId, clientName);
    }
}
