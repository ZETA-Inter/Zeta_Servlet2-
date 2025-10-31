package com.zeta_servlet.model;


import java.util.List;

public class Aula {
    int id;
    String nome;
    int id_modulo;
    List<Texto_corrido> texto_corridos;
    List<Flash_card> flashCards;
    List<Lei> leis;


    public Aula(int id, String nome, int id_modulo, List<Texto_corrido> texto_corridos, List<Flash_card> flashCards, List<Lei> leis) {
        this.id = id;
        this.id_modulo = id_modulo;
        this.nome = nome;
        this.texto_corridos = texto_corridos;
        this.flashCards = flashCards;
        this.leis = leis;
        for (int i = 0; i < leis.size(); i++) {
            leis.get(i).setId_aula(this.id);
        }
        for (int i = 0; i < texto_corridos.size(); i++) {
            texto_corridos.get(i).setId_aula(this.id);
        }
        for (int i = 0; i < flashCards.size(); i++) {
            flashCards.get(i).setId_aula(this.id);
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

    public List<Texto_corrido> getTexto_corridos() {
        return texto_corridos;
    }

    public List<Flash_card> getFlashCards() {
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
                ", texto_corridos=" + texto_corridos +
                ", flashCards=" + flashCards +
                ", leis=" + leis +
                '}';
    }
}

