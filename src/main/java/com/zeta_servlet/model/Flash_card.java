package com.zeta_servlet.model;

public class Flash_card {
    private int id;
    private String frente;
    private String verso;
    private int id_aula;

    public Flash_card(int id, String frente,String verso, int id_aula) {
        this.id = id;
        this.frente = frente;
        this.verso = verso;
        this.id_aula = id_aula;
    }

    public int getId() {
        return id;
    }

    public String getFrente() {return frente;}

    public String getVerso() {return verso;}

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }


    public String toString() {
        return "Flash_card{" +
                "id=" + id +
                ", frente='" + frente + '\'' +
                ", verso='" + verso + '\'' +
                ", id_aula=" + id_aula +
                '}';
    }
}
