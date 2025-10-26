package com.zeta_servlet.model;


public class Fornecedor{
    private int id;
    private int idEmpresa;
    private String nomePri;
    private int idAssinatura;
    private String nomeUlt;
    private String email;
    private String senha;

    private String telefone;


    public Fornecedor(int id, int idEmpresa, String nomePri, int idAssinatura, String nomeUlt, String email, String senha, String telefone) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.nomePri = nomePri;
        this.idAssinatura = idAssinatura;
        this.nomeUlt = nomeUlt;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public int getIdEmpresa() {return idEmpresa;}

    public int getIdAssinatura() {return idAssinatura;}

    public String getNomePri() {
        return nomePri;
    }

    public String getNomeUlt() {
        return nomeUlt;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {return telefone;}

    @Override
    public String toString() {
        return "\nID: "+id+"\n"+
                "Email: "+email+"\n"+
                "Senha: "+senha+"\n"+
                "PrimeiroNome: "+nomePri+"\n"+
                "UltimoNome: "+nomeUlt+"\n"+
                "IDAssinatura: "+idAssinatura+"\n"+
                "IDEmpresa: "+idEmpresa+"\n"+
                "Telefone: "+telefone+"\n"+
                "=".repeat(30);
    }
}

