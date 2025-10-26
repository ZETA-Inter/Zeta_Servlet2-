package com.zeta_servlet.model;

public class Adm {
    private String email;
    private int id;
    private String senha;

    public Adm(String email, int id, String senha) {
        this.email = email;
        this.id = id;
        this.senha = senha;
    }//contrutor

    public String getEmail() {
        return this.email;
    }

    public int getId() {
        return this.id;
    }

    public String getSenha() {
        return this.senha;
    }

    @Override
    public String toString() {
        return "\nID: "+id+"\n"+
                "Email: "+email+"\n"+
                "Senha: "+senha+"\n"+
                "=".repeat(30);
    }
}
