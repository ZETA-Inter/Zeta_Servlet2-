package com.zeta_servlet.model;


public class Alternativa {
    int id;
    String alternativa;
    int id_atividade;
    boolean correto;

    public Alternativa(int id, String alternativa, int id_atividade, boolean correto) {
        this.id = id;
        this.alternativa = alternativa;
        this.id_atividade = id_atividade;
        this.correto = correto;
    }

    public int getId() {
        return id;
    }

    public boolean isCorreto() {return correto;}

    public String getAlternativa() {
        return alternativa;
    }

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }

    public String toString() {
        return
                "   \nId: " + id +"\n"+
                "   Alternativa: " + alternativa +"\n"+
                "   Id_atividade: " + id_atividade+"\n"+
                        "   "+"=".repeat(30);

    }
}
