package com.sd.challenge.infra.jsf.pagebeans.user;

import com.sd.challenge.application.requests.user.CreateUserRequest;
import com.sd.challenge.application.usecases.user.CreateUserUseCase;
import com.sd.challenge.infra.jsf.utils.JsfMessageUtil;
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

    @Inject
    private JsfMessageUtil jsfMessageUtil;

    public void execute() {
        try {
            this.createUserUseCase.execute(user);
            this.jsfMessageUtil.add("Account created successfully!");
            this.user = new CreateUserRequest();
        } catch (RuntimeException exception){
            this.jsfMessageUtil.add(exception.getMessage());
        }
    }
}
