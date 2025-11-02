package com.zeta_servlet.model;

public class FlashCard {
    private int id;
    private String frente;
    private String verso;
    private int idAula;

    public FlashCard(int id, String frente, String verso, int idAula) {
        this.id = id;
        this.frente = frente;
        this.verso = verso;
        this.idAula = idAula;
    }

    public int getId() {
        return id;
    }

    public String getFrente() {return frente;}

    public String getVerso() {return verso;}

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }


    public String toString() {
        return "Flash_card{" +
                "id=" + id +
                ", frente='" + frente + '\'' +
                ", verso='" + verso + '\'' +
                ", id_aula=" + idAula +
                '}';
    }
}
