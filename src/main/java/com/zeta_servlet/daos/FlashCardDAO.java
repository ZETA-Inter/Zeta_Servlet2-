package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.FlashCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlashCardDAO extends CRUD{

//  insere um flash card no banco
    public int inserir(FlashCard flash) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into flash_card(frente, verso, id_aula) values(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, flash.getFrente());
            pstmt.setString(2, flash.getVerso());
            pstmt.setInt(3, flash.getIdAula());



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

//    altera o flash card frente
    public int updateFrente(FlashCard flash) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE flash_card SET frente = ? WHERE id = ?;");
            pstm.setString(1, flash.getFrente());
            pstm.setInt(2, flash.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
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

    //    altera o flash card verso
    public int updateVerso(FlashCard flash) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE flash_card SET verso = ? WHERE id = ?;");
            pstm.setString(1, flash.getVerso());
            pstm.setInt(2, flash.getId());
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
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

//    altera a aula do flash card
    public int updateIdAula(FlashCard flash) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE flash_card SET id_aula = ? WHERE id = ?;");
            pstm.setInt(1, flash.getIdAula());
            pstm.setInt(2, flash.getId());
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

//    remove um flash card
    public boolean remover(int id) {return super.remover(id, "flash_card");}

//    seleciona todos os flash cards da tabela
    public List<FlashCard> buscar() {
        //query
        List<FlashCard> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rset = buscarR("flash_card");



            while (rset.next()) {
                FlashCard flash = new FlashCard(rset.getInt("id"), rset.getString("frente"), rset.getString("verso"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }

    public List<FlashCard> buscarPorId(int id) {
        //query
        List<FlashCard> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rset = buscarPorIdR(id, "flash_card");



            while (rset.next()) {
                FlashCard flash = new FlashCard(rset.getInt("id"), rset.getString("frente"), rset.getString("verso"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }


//    busca o item da tabela com base no flash card
    public List<FlashCard> buscarPorFlashCard(String flash_card) {
        //query
        List<FlashCard> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM flash_card WHERE flash_card = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, flash_card);
            rset = pstm.executeQuery();




            while (rset.next()) {
                FlashCard flash = new FlashCard(rset.getInt("id"), rset.getString("frente"), rset.getString("verso"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }

//    seleciona o flash card com base na aula
    public List<FlashCard> buscarPorIdAula(int id_aula) {
        //query
        List<FlashCard> liF = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM flash_card WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id_aula);
            rset = pstm.executeQuery();




            while (rset.next()) {
                FlashCard flash = new FlashCard(rset.getInt("id"), rset.getString("frente"), rset.getString("verso"), rset.getInt("id_aula"));
                liF.add(flash);
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
            return liF;
        }
    }


}
