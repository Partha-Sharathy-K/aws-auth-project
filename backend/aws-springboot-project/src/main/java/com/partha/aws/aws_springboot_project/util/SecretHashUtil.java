package com.partha.aws.aws_springboot_project.util;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class SecretHashUtil {

    public String calculateSecretHash(String username, String clientId, String clientSecret) {
        try {
            String message = username + clientId;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return Base64.getEncoder()
                    .encodeToString(mac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException("Error creating secret hash", e);
        }
    }
}

