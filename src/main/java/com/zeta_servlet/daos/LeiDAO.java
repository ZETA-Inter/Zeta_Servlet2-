package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Lei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeiDAO extends CRUD{

//    insere uma lei na tabela

    public int inserir(Lei lei) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into lei(lei, id_aula) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, lei.getLei());
            pstmt.setInt(2, lei.getId_aula());



            if (pstmt.executeUpdate() >0){
                return 1;
            }
            return 0;
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
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

//    altera a lei usada
    public int updateLei(Lei lei) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE lei SET lei = ? WHERE id = ?;");
            pstm.setString(1, lei.getLei());
            pstm.setInt(2, lei.getId());
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


//    altera a aula referente a lei
    public int updateIdAula(Lei lei) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE lei SET id_aula = ? WHERE id = ?;");
            pstm.setInt(1, lei.getId_aula());
            pstm.setInt(2, lei.getId());
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

//    remove uma lei
    public boolean remover(int id) {return super.remover(id, "lei");}

//    seleciona todas as leis da tabela
    public List<Lei> buscar() {
        //query
        List<Lei> liL = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            rset = buscarR("texto_corrido");
            rset = buscarR("flash_card");
            while (rset.next()) {
                Lei lei = new Lei(rset.getInt("id"), rset.getString("lei"), rset.getInt("id_aula"));
                liL.add(lei);
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
            return liL;
        }
    }

//    busca a lei com base no ID
    public List<Lei> buscarPorId(int id) {
        //query
        List<Lei> liL = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM lei WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Lei lei = new Lei(rset.getInt("id"), rset.getString("lei"), rset.getInt("id_aula"));
                liL.add(lei);
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
            return liL;
        }
    }


//    busca a lei na tabela
    public List<Lei> buscarPorLei(String lei) {
        //query
        List<Lei> liL = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM lei WHERE lei = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, lei);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Lei lei1 = new Lei(rset.getInt("id"), rset.getString("lei"), rset.getInt("id_aula"));
                liL.add(lei1);
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
            return liL;
        }
    }

//    busca lei pela aula
    public List<Lei> buscarPorIdAula(int id_aula) {
        //query
        List<Lei> liL = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM lei WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id_aula);
            rset = pstm.executeQuery();




            while (rset.next()) {
                Lei lei = new Lei(rset.getInt("id"), rset.getString("lei"), rset.getInt("id_aula"));
                liL.add(lei);
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
            return liL;
        }
    }


}

