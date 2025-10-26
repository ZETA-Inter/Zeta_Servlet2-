package com.zeta_servlet.model;

public class Curso {

    private int id;
    private String nome;
    private String segmento;
    private String descricao;

    public Curso(int id, String nome, String segmento, String descricao) {
        this.id = id;
        this.nome = nome;
        this.segmento = segmento;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSegmento() {
        return segmento;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Curso: " +
                "\nid = " + id +
                "\nnome = " + nome +
                "\nsegmento = " + segmento +
                "\ndescricao = " + descricao;
    }
}
