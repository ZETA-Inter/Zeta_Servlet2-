package com.zeta_servlet.model;


import java.math.BigDecimal;

public class Assinatura {
    private int id;
    private String tpPlano;
    private int benefQtdCursos;
    private String benefDescPlno;
    private BigDecimal precoFixo;
    private BigDecimal precoQtdProdutores;

    public Assinatura(int id, String tpPlano, int benefQtdCursos, String benefDescPlno, BigDecimal precoFixo, BigDecimal precoQtdProdutores) {
        this.id = id;
        this.tpPlano = tpPlano;
        this.benefQtdCursos = benefQtdCursos;
        this.benefDescPlno = benefDescPlno;
        this.precoFixo = precoFixo;
        this.precoQtdProdutores = precoQtdProdutores;
    }

    @Override
    public String toString() {
        return "\nID: "+id+"\n"+
                "TipoPlano: "+tpPlano+"\n"+
                "BeneficioQtdCursos: "+benefQtdCursos+"\n"+
                "BeneficiosDescricao: "+benefDescPlno+"\n"+
                "PrecoFixo: "+precoFixo+"\n"+
                "PrecoQtdProdutores: "+precoQtdProdutores+"\n"+
                "=".repeat(30);
    }

    public int getId() {
        return id;
    }

    public String getTpPlano() {
        return tpPlano;
    }

    public int getBenefQtdCursos() {
        return benefQtdCursos;
    }

    public String getBenefDescPlno() {
        return benefDescPlno;
    }

    public BigDecimal getPrecoFixo() {
        return precoFixo;
    }

    public BigDecimal getPrecoQtdProdutores() {
        return precoQtdProdutores;
    }
}
