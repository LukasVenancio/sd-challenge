package com.sd.challenge.application.mappers;

import com.sd.challenge.application.requests.user.CreateUserRequest;
import com.sd.challenge.domain.entities.User;

public interface UserBoundaryMapper {
    User toEntity(CreateUserRequest createUserRequest);
}
