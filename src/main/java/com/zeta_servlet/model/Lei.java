package com.zeta_servlet.model;




public class Lei {
    int id;
    String lei;
    int idAula;

    public Lei(int id, String lei, int idAula) {
        this.id = id;
        this.lei = lei;
        this.idAula = idAula;
    }

    public int getId() {
        return id;
    }

    public String getLei() {
        return lei;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int id_aula) {
        this.idAula = idAula;
    }

    public String toString() {
        return "Lei{" +
                "id=" + id +
                ", lei='" + lei + '\'' +
                ", id_aula=" + idAula +
                '}';
    }
}
