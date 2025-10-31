package com.zeta_servlet.model;


public class Alternativa {
    int id;
    String alternativa;
    int idAtividade;
    boolean correto;

    public Alternativa(int id, String alternativa, int idAtividade, boolean correto) {
        this.id = id;
        this.alternativa = alternativa;
        this.idAtividade = idAtividade;
        this.correto = correto;
    }

    public int getId() {
        return id;
    }

    public boolean isCorreto() {return correto;}

    public String getAlternativa() {
        return alternativa;
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int id_atividade) {
        this.idAtividade = idAtividade;
    }

    public String toString() {
        return
                "   \nId: " + id +"\n"+
                "   Alternativa: " + alternativa +"\n"+
                "   Id_atividade: " + idAtividade+"\n"+
                        "   "+"=".repeat(30);

    }
}
