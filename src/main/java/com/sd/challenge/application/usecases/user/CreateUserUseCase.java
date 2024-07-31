package com.sd.challenge.application.usecases.user;

import com.sd.challenge.application.errors.DuplicatedResourceException;
import com.sd.challenge.application.errors.InternalServerErrorException;
import com.sd.challenge.application.mappers.UserBoundaryMapper;
import com.sd.challenge.application.repositories.user.UserRepository;
import com.sd.challenge.application.requests.user.CreateUserRequest;
import com.sd.challenge.application.utils.PasswordUtil;
import com.sd.challenge.domain.entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

@RequestScoped
@NoArgsConstructor
public class CreateUserUseCase {
    @Inject
    private UserRepository userRepository;

    @Inject
    private UserBoundaryMapper userBoundaryMapper;

    @Inject
    private PasswordUtil passwordUtil;

    public void execute(CreateUserRequest createUserRequest) {
        if (this.userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) throw new DuplicatedResourceException(User.class);

        var user = this.userBoundaryMapper.toEntity(createUserRequest);
        user.setPassword(this.getEncryptedPassword(createUserRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(user);
    }

    private String getEncryptedPassword(String password) throws InternalServerErrorException {
        var salt = this.passwordUtil.generateSalt();
        try {
            return this.passwordUtil.encrypt(password,salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InternalServerErrorException();
        }
    }
}
