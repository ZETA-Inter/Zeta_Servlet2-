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

    public String getTexto_corrido() {
        return textoCorrido;
    }

    public int getId_aula() {
        return idAula;
    }

    public void setIdAula(int id_aula) {
        this.idAula = idAula;
    }

    @Override
    public String toString() {
        return "Texto_corrido{" +
                "id=" + id +
                ", texto_corrido='" + textoCorrido + '\'' +
                ", id_aula=" + idAula +
                '}';
    }
}

