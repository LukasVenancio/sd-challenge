package com.sd.challenge.infra.postgres.repositories;

import com.sd.challenge.application.repositories.user.UserRepository;
import com.sd.challenge.domain.entities.User;
import com.sd.challenge.infra.mappers.UserMapperImpl;
import com.sd.challenge.infra.postgres.models.UserModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserMapperImpl userMapper;

    public void save(User user) {
        entityManager.persist(user);
//       entityManager.persist(userMapper.toModel(user));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMapper.toEntity(entityManager.find(UserModel.class, email)));
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userMapper.toEntity(entityManager.find(UserModel.class, id)));
    }

    public void disable(UUID id) {
        var foundUser = findById(id).orElseThrow(RuntimeException::new);
        foundUser.setDisabledAt(LocalDateTime.now());

        entityManager.persist(userMapper.toModel(foundUser));
    }
}
