package com.partha.aws.aws_springboot_project.repository;

import com.partha.aws.aws_springboot_project.document.UserToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserTokenRepository
        extends MongoRepository<UserToken, String> {

    Optional<UserToken> findByAccessToken(String accessToken);
}
