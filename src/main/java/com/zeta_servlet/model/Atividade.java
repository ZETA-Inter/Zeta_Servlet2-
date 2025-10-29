package com.zeta_servlet.model;


import java.util.List;

public class Atividade {
    int id;
    double pontuacao;
    int id_aula;
    String imagens_url;

    List<Pergunta> perguntas;
    List<Alternativa> alternativas;

    public Atividade(int id, double pontuacao, int id_aula, String imagens_url, List<Pergunta> perguntas, List<Alternativa> alternativas) {
        this.id = id;
        this.pontuacao = pontuacao;
        this.id_aula = id_aula;
        this.imagens_url = imagens_url;
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

    public String getImagens_url() {
        return imagens_url;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", pontuacao=" + pontuacao +
                ", id_aula=" + id_aula +
                ", imagens_url='" + imagens_url + '\'' +
                ", perguntas=" + perguntas +
                ", alternativas=" + alternativas;
    }
}
