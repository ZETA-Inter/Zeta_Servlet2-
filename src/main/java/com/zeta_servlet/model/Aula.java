package com.zeta_servlet.model;


import java.util.List;

public class Aula {
    int id;
    String nome;
    int id_modulo;
    List<TextoCorrido> textoCorridos;
    List<FlashCard> flashCards;
    List<Lei> leis;


    public Aula(int id, String nome, int id_modulo, List<TextoCorrido> textoCorridos, List<FlashCard> flashCards, List<Lei> leis) {
        this.id = id;
        this.id_modulo = id_modulo;
        this.nome = nome;
        this.textoCorridos = textoCorridos;
        this.flashCards = flashCards;
        this.leis = leis;
        for (int i = 0; i < leis.size(); i++) {
            leis.get(i).setIdAula(this.id);
        }
        for (int i = 0; i < textoCorridos.size(); i++) {
            textoCorridos.get(i).setIdAula(this.id);
        }
        for (int i = 0; i < flashCards.size(); i++) {
            flashCards.get(i).setIdAula(this.id);
        }
    }

    public Aula(int id, String nome, int id_modulo) {
        this.id = id;
        this.id_modulo = id_modulo;
        this.nome = nome;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdModulo() {
        return id_modulo;
    }

    public List<TextoCorrido> getTexto_corridos() {
        return textoCorridos;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public List<Lei> getLeis() {
        return leis;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", id_modulo=" + id_modulo +
                ", texto_corridos=" + textoCorridos +
                ", flashCards=" + flashCards +
                ", leis=" + leis +
                '}';
    }
}

