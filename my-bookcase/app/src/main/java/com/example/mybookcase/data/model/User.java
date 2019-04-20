package com.example.mybookcase.data.model;

public class User {

    private String nomeCompleto;
    private String email;
    private String senha;

    public User( String email, String nomeCompleto, String senha){
        this.setEmail(email);
        this.setNomeCompleto(nomeCompleto);
        this.setSenha(senha);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
