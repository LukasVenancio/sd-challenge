package com.sd.challenge.infra.jsf.pagebeans.user;

import com.sd.challenge.application.requests.user.CreateUserRequest;
import com.sd.challenge.application.usecases.user.CreateUserUseCase;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter
@Setter
public class CreateUserPageBean {
    private CreateUserRequest user = new CreateUserRequest();

    @Inject
    private CreateUserUseCase createUserUseCase;

    public void execute(){
        createUserUseCase.execute(user);
        System.out.println(user.getEmail());
    }
}
