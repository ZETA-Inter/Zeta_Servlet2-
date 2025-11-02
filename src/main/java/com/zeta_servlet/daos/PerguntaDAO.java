package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Pergunta;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerguntaDAO extends CRUD{

    //    insere um item na tabela
    public int inserir(Pergunta pt) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {


            //query alternativas
            String consultaFlash = "insert into pergunta(pergunta, id_ativade) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consultaFlash);
            pstmt.setString(1, pt.getPergunta());
            pstmt.setInt(2, pt.getIdAtividade());


            if (pstmt.executeUpdate() >0){
                return 1;
            }
            return 0;
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace();
            return -1;
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

//  altera a pontuação da atividade

    public int updatePergunta(Pergunta pt, String pergunta) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE pergunta SET pergunta = ? WHERE id = ?;");
            pstm.setString(1, pergunta);
            pstm.setInt(2, pt.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(coon);
        }
    }

    public int updateIdAtividade (Pergunta pt, int idAtividade) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE pergunta SET id_atividade = ? WHERE id = ?;");
            pstm.setInt(1, idAtividade);
            pstm.setInt(2, pt.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(coon);
        }
    }


    //    remove uma atividade
    public boolean remover(int id) {return super.remover(id, "pergunta");}

    //    seleciona todas as atividades da tabela
    public List<Pergunta> buscar() {
        //query
        List<Pergunta> liPT = new ArrayList<>();
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {


            rsetP = buscarR("Pergunta");
            while (rsetP.next()) {
                Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                liPT.add(pergunta);
            }
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liPT;
        }
    }

//    busca uma atividade por ID

    public List<Pergunta> buscarPorId(int id) {
        //query

        List<Pergunta> liPT = new ArrayList<>();
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {



            rsetP = buscarPorIdR(id, "pergunta");
            while (rsetP.next()) {
                Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                liPT.add(pergunta);
            }
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liPT;
        }
    }


//    busca atividades com base na pontuação

    public List<Pergunta> buscarPorPergunta(String pergunta) {
        //query
        List<Pergunta> liPT = new ArrayList<>();

        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM pergunta WHERE pergunta = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, pergunta);
            rsetP = pstm.executeQuery();


            while (rsetP.next()) {
                Pergunta pergunta1 = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                liPT.add(pergunta1);
            }

        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liPT;
        }
    }


//    busca atividade com base na aula

    public List<Pergunta> buscarPorIdAula(int idAula) {
        //query
        List<Pergunta> liPT = new ArrayList<>();

        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM pergunta WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, idAula);
            rsetP = pstm.executeQuery();


            while (rsetP.next()) {
                Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                liPT.add(pergunta);
            }

        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liPT;
        }
    }
}
