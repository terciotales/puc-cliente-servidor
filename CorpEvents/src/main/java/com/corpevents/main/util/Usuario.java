package com.corpevents.main.util;

import com.corpevents.main.model.Pessoa;

public class Usuario {
    private static Usuario instance;

    private Pessoa pessoa;

    private Boolean isLogged = false;

    private Usuario() {
    }

    public static Usuario getInstance() {
        if (instance == null) {
            instance = new Usuario();
        }
        return instance;
    }

    public boolean isAdministrador() {
        return pessoa.getRole() == 1;
    }

    public boolean isPadrao() {
        return pessoa.getRole() == 2;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Boolean isLogged() {
        return isLogged;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }
}
