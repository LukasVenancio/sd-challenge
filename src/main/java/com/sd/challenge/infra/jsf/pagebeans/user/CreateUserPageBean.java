package com.sd.challenge.infra.jsf.pagebeans.user;

import com.sd.challenge.application.requests.user.CreateUserRequest;
import com.sd.challenge.application.usecases.user.CreateUserUseCase;
import jakarta.enterprise.context.RequestScoped;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
    private FacesContext facesContext;

    public void execute() {
        this.createUserUseCase.execute(user);
        facesContext.addMessage(null, new FacesMessage("Account created successfully!"));
        this.user = new CreateUserRequest();
    }
}
