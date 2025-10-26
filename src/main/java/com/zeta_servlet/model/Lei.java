package com.zeta_servlet.model;




public class Lei {
    int id;
    String lei;
    int id_aula;

    public Lei(int id, String lei, int id_aula) {
        this.id = id;
        this.lei = lei;
        this.id_aula = id_aula;
    }

    public int getId() {
        return id;
    }

    public String getLei() {
        return lei;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String toString() {
        return "Lei{" +
                "id=" + id +
                ", lei='" + lei + '\'' +
                ", id_aula=" + id_aula +
                '}';
    }
}
