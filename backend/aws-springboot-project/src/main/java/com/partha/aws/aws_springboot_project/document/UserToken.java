package com.partha.aws.aws_springboot_project.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_tokens")
@Data
public class UserToken {
    @Id
    private String id;

    private String username;
    private String accessToken;
    private String refreshToken;
    private String idToken;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAt;

    private boolean loggedOut;
    private LocalDateTime createdAt;
}

