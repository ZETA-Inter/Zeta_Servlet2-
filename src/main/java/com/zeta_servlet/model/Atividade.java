package com.zeta_servlet.model;


import java.util.List;

public class Atividade {
    int id;
    double pontuacao;
    int id_aula;

    List<Pergunta> perguntas;
    List<Alternativa> alternativas;

    public Atividade(int id, double pontuacao, int id_aula, List<Pergunta> perguntas, List<Alternativa> alternativas) {
        this.id = id;
        this.pontuacao = pontuacao;
        this.id_aula = id_aula;
        this.perguntas = perguntas;
        this.alternativas = alternativas;
        for (int i = 0; i < perguntas.size(); i++) {
            perguntas.get(i).setId_atividade(this.id);
        }
        for (int i = 0; i < alternativas.size(); i++) {
            alternativas.get(i).setId_atividade(this.id);
        }
    }

    public Atividade(double pontuacao, int id_aula, List<Pergunta> perguntas, List<Alternativa> alternativas) {
        this.pontuacao = pontuacao;
        this.id_aula = id_aula;
        this.perguntas = perguntas;
        this.alternativas = alternativas;
        for (int i = 0; i < perguntas.size(); i++) {
            perguntas.get(i).setId_atividade(this.id);
        }
        for (int i = 0; i < alternativas.size(); i++) {
            alternativas.get(i).setId_atividade(this.id);
        }
    }

    public int getId() {
        return id;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public int getId_aula() {
        return id_aula;
    }


    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    @Override
    public String toString() {
        return
                "\nId" + id +"\n"+
                "Pontuacao=" + pontuacao +"\n"+
                "Id_aula=" + id_aula +"\n"+
                "Perguntas=" + perguntas +"\n"+
                "Alternativas=" + alternativas+"\n"+
                 "=".repeat(30);
    }
}
