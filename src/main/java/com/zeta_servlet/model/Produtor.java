package com.zeta_servlet.model;

import java.time.LocalDate;

public class Produtor {
    private int id;
    private String cpf;
    private LocalDate dt_nascimento;
    private String email;
    private String senha;
    private int pontos_acumulados;
    private String nome_primeiro;
    private String nome_ultimo;
    private int aulas_feitas;
    private int id_fornecedor;
    private int id_assinatura;


    public Produtor(int id, String cpf, LocalDate dt_nascimento, String email, String senha, int pontos_acumulados, String nome_primeiro, String nome_ultimo, int aulas_feitas, int id_fornecedor, int id_assinatura) {
        this.id = id;
        this.cpf = cpf;
        this.dt_nascimento = dt_nascimento;
        this.email = email;
        this.senha = senha;
        this.pontos_acumulados = pontos_acumulados;
        this.nome_primeiro = nome_primeiro;
        this.nome_ultimo = nome_ultimo;
        this.aulas_feitas = aulas_feitas;
        this.id_fornecedor = id_fornecedor;
        this.id_assinatura = id_assinatura;
    }

    public int getId() {
        return this.id;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getPontos_acumulados() {
        return pontos_acumulados;
    }

    public String getNome_primeiro() {
        return nome_primeiro;
    }

    public String getNome_ultimo() {
        return nome_ultimo;
    }

    public int getAulas_feitas() {
        return aulas_feitas;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public int getId_assinatura() {
        return id_assinatura;

    }

    @Override
    public String toString() {
        return "Produtor: " +
                "id = " + id +
                "\tcpf = " + cpf + '\'' +
                "dt_nascimetno = " + dt_nascimento +
                "email = " + email + '\'' +
                "senha = " + senha + '\'' +
                "pontos_acumulados = " + pontos_acumulados +
                "nome_primeiro = " + nome_primeiro + '\'' +
                "nome_ultimo = " + nome_ultimo + '\'' +
                "aulas_feitas = " + aulas_feitas +
                "id_fornecedor = " + id_fornecedor +
                "id_assinatura = " + id_assinatura +
                '}';
    }
}

