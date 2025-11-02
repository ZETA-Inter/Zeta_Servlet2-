package com.zeta_servlet.model;

public class Pergunta {
    int id;
    String pergunta;
    int idAtividade;

    public Pergunta(int id, String pergunta, int idAtividade) {
        this.id = id;
        this.pergunta = pergunta;
        this.idAtividade = idAtividade;
    }

    public Pergunta(String pergunta, int id_atividade){
        this.pergunta = pergunta;
        this.idAtividade = idAtividade;
    }

    public int getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String toString() {
        return "    \nId: " + id +"\n"+
                "   Pergunta: " + pergunta + "\n" +
                "   Id_atividade=" + idAtividade+"\n"+
                "   "+"=".repeat(30);
    }
}