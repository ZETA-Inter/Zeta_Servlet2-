package com.zeta_servlet.model;


public class Texto_corrido {
    private int id;
    private String texto_corrido;
    private int id_aula;

    public Texto_corrido(int id, String texto_corrido, int id_aula) {
        this.id = id;
        this.texto_corrido = texto_corrido;
        this.id_aula = id_aula;
    }

    public int getId() {
        return id;
    }

    public String getTexto_corrido() {
        return texto_corrido;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    @Override
    public String toString() {
        return "Texto_corrido{" +
                "id=" + id +
                ", texto_corrido='" + texto_corrido + '\'' +
                ", id_aula=" + id_aula +
                '}';
    }
}

