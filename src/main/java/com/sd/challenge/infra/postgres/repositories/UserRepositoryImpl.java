package com.sd.challenge.infra.postgres.repositories;

import com.sd.challenge.application.repositories.user.UserRepository;
import com.sd.challenge.domain.entities.User;
import com.sd.challenge.infra.mappers.UserMapperImpl;
import com.sd.challenge.infra.postgres.models.UserModel;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserMapperImpl userMapper;

    @Transactional
    public void save(User user) {
        var model = userMapper.toModel(user);
        entityManager.persist(model);
    }

    public Optional<User> findByEmail(String email) {
        var query = entityManager.createQuery("SELECT u FROM users u WHERE u.email = :email", UserModel.class);
        query.setParameter("email", email);
        query.setMaxResults(1);

        try {
            UserModel user = query.getSingleResult();
            return Optional.ofNullable(userMapper.toEntity(user));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userMapper.toEntity(entityManager.find(UserModel.class, id)));
    }

    @Transactional
    public void disable(UUID id) {
        var foundUser = findById(id).orElseThrow(RuntimeException::new);
        foundUser.setDisabledAt(LocalDateTime.now());

        entityManager.merge(userMapper.toModel(foundUser));
    }
}
