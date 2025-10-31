package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Alternativa;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlternativaDAO extends CRUD{

    //    insere um item na tabela
    public int inserir(Alternativa at) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {


            //query alternativas
                String consultaFlash = "insert into alternativa(alternativa, id_ativade, correto) values(?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(consultaFlash);
                pstmt.setString(1, at.getAlternativa());
                pstmt.setInt(2, at.getIdAtividade());
                pstmt.setBoolean(3, at.isCorreto());




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

    public int updateAlternativa(Alternativa at, String alternativa) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE alternativa SET alternativa = ? WHERE id = ?;");
            pstm.setString(1, alternativa);
            pstm.setInt(2, at.getId());
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

    public int updateIdAtividade(Alternativa at, int idAtividade) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE alternativa SET id_atividade = ? WHERE id = ?;");
            pstm.setInt(1, idAtividade);
            pstm.setInt(2, at.getId());
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

    public int updateCorreto(Alternativa at, boolean correto) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE alternativa SET correto = ? WHERE id = ?;");
            pstm.setBoolean(1, correto);
            pstm.setInt(2, at.getId());
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
    public boolean remover(int id) {return super.remover(id, "alternativa");}

    //    seleciona todas as atividades da tabela
    public List<Alternativa> buscar() {
        //query
        List<Alternativa> liAL = new ArrayList<>();
        ResultSet rsetAL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {


                rsetAL = buscarR("alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
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
            return liAL;
        }
    }

//    busca uma atividade por ID

    public List<Alternativa> buscarPorId(int id) {
        //query

        List<Alternativa> liAL = new ArrayList<>();
        ResultSet rsetAL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {



                rsetAL = buscarPorIdR(id, "alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
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
            return liAL;
        }
    }


//    busca atividades com base na pontuação

    public List<Alternativa> buscarPorAlternativa(String alternativa) {
        //query
        List<Alternativa> liAL = new ArrayList<>();

        ResultSet rsetAL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM alternativa WHERE alternativa = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, alternativa);
            rsetAL = pstm.executeQuery();


                while (rsetAL.next()) {
                    Alternativa alternativa1 = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa1);
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
            return liAL;
        }
    }


//    busca atividade com base na aula

    public List<Alternativa> buscarPorIdAula(int id) {
        //query
        List<Alternativa> liAL = new ArrayList<>();

        ResultSet rsetAL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM alternativa WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rsetAL = pstm.executeQuery();


            while (rsetAL.next()) {
                Alternativa alternativa1 = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                liAL.add(alternativa1);
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
            return liAL;
        }
    }




    public List<Alternativa> buscarPorCorreto(boolean correto) {
        //query
        List<Alternativa> liAL = new ArrayList<>();

        ResultSet rsetAL = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM alternativa WHERE correto = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setBoolean(1, correto);
            rsetAL = pstm.executeQuery();


            while (rsetAL.next()) {
                Alternativa alternativa1 = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                liAL.add(alternativa1);
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
            return liAL;
        }
    }
}
