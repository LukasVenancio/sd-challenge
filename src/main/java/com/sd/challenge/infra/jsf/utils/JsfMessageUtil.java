package com.sd.challenge.infra.jsf.utils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@RequestScoped
public class JsfMessageUtil {

    @Inject
    private FacesContext facesContext;

    public void add(String clientId, String message) {
        facesContext.addMessage(clientId, new FacesMessage(message));
    }

    public void add(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }
}
