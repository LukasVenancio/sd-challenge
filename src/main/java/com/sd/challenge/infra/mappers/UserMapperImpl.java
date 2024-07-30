package com.sd.challenge.infra.mappers;

import com.sd.challenge.application.mappers.UserBoundaryMapper;
import com.sd.challenge.application.requests.user.CreateUserRequest;
import com.sd.challenge.domain.entities.User;
import com.sd.challenge.infra.postgres.models.UserModel;
import jakarta.enterprise.context.Dependent;

@Dependent
public class UserMapperImpl implements UserBoundaryMapper {

    public User toEntity(CreateUserRequest createUserRequest) {
       var user = new User();
       user.setEmail(createUserRequest.getEmail());
       user.setPassword(createUserRequest.getPassword());
       user.setFirstName(createUserRequest.getFirstName());
       user.setLastName(createUserRequest.getLastName());
       return user;
    }

    public UserModel toModel(User entity) {
        var model = new UserModel();
        model.setId(entity.getId());
        model.setEmail(entity.getEmail());
        model.setPassword(entity.getPassword());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setDisabledAt(entity.getDisabledAt());
        return model;
    }

    public User toEntity(UserModel model) {
        var entity = new User();
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setDisabledAt(model.getDisabledAt());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        entity.setId(model.getId());
        return entity;
    }
}
