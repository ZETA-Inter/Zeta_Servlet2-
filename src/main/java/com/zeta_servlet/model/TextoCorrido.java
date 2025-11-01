package com.zeta_servlet.model;


public class TextoCorrido {
    private int id;
    private String textoCorrido;
    private int idAula;

    public TextoCorrido(int id, String textoCorrido, int idAula) {
        this.id = id;
        this.textoCorrido = textoCorrido;
        this.idAula = idAula;
    }

    public int getId() {
        return id;
    }

    public String getTextoCorrido() {
        return textoCorrido;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {this.idAula = idAula;}

    @Override
    public String toString() {
        return "Texto_corrido{" +
                "id=" + id +
                ", texto_corrido='" + textoCorrido + '\'' +
                ", id_aula=" + idAula +
                '}';
    }
}

