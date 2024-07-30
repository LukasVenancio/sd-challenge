package com.sd.challenge.application.usecases.user;

import com.sd.challenge.application.mappers.UserBoundaryMapper;
import com.sd.challenge.application.repositories.user.UserRepository;
import com.sd.challenge.application.requests.user.CreateUserRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

@RequestScoped
@NoArgsConstructor
public class CreateUserUseCase {
    @Inject
    private UserRepository userRepository;

    @Inject
    private UserBoundaryMapper userBoundaryMapper;

    @Transactional
    public void execute(CreateUserRequest createUserRequest) {
        if (this.userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) throw new RuntimeException();

        var user = this.userBoundaryMapper.toEntity(createUserRequest);
        this.userRepository.save(user);
    }
}
