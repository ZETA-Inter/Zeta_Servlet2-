package com.zeta_servlet.model;

public class Pergunta {
    int id;
    String pergunta;
    int id_atividade;

    public Pergunta(int id, String pergunta, int id_atividade) {
        this.id = id;
        this.pergunta = pergunta;
        this.id_atividade = id_atividade;
    }

    public int getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }

    public String toString() {
        return "    \nId: " + id +"\n"+
                "   Pergunta: " + pergunta + "\n" +
                "   Id_atividade=" + id_atividade+"\n"+
                "   "+"=".repeat(30);
    }
}