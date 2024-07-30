package com.sd.challenge.application.repositories.user;

import com.sd.challenge.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    void disable(UUID id);

}