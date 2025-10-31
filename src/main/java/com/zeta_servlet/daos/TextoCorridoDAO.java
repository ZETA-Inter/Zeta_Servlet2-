package com.zeta_servlet.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.model.TextoCorrido;

public class TextoCorridoDAO extends CRUD{

    public int inserir(TextoCorrido texto) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into texto_corrido(texto_corrido, id_aula) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, texto.getTexto_corrido());
            pstmt.setInt(2, texto.getId_aula());



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

    public int updateTextoCorrido(TextoCorrido texto) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE texto_corrido SET texto_corrido = ? WHERE id = ?;");
            pstm.setString(1, texto.getTexto_corrido());
            pstm.setInt(2, texto.getId());
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

    public int updateIdAula(TextoCorrido texto) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE texto_corrido SET id_aula = ? WHERE id = ?;");
            pstm.setInt(1, texto.getId_aula());
            pstm.setInt(2, texto.getId());
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

    public boolean remover(int id) {return super.remover(id, "texto_corrido");}

    public List<TextoCorrido> buscar() {
        //query
        List<TextoCorrido> liTE = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            rset = buscarR("texto_corrido");
            while (rset.next()) {
                TextoCorrido texto = new TextoCorrido(rset.getInt("id"), rset.getString("texto_corrido"), rset.getInt("id_aula"));
                liTE.add(texto);
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
            return liTE;
        }
    }

    public List<TextoCorrido> buscarPorId(int id) {
        //query
        List<TextoCorrido> liTE = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM texto_corrido WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();




            while (rset.next()) {
                TextoCorrido texto = new TextoCorrido(rset.getInt("id"), rset.getString("texto_corrido"), rset.getInt("id_aula"));
                liTE.add(texto);
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
            return liTE;
        }
    }

    public List<TextoCorrido> buscarPorTextoCorrido(String lei) {
        //query
        List<TextoCorrido> liTE = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM texto_corrido WHERE lei = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, lei);
            rset = pstm.executeQuery();




            while (rset.next()) {
                TextoCorrido texto = new TextoCorrido(rset.getInt("id"), rset.getString("texto_corrido"), rset.getInt("id_aula"));
                liTE.add(texto);
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
            return liTE;
        }
    }

    public List<TextoCorrido> buscarPorIdAula(int id_aula) {
        //query
        List<TextoCorrido> liTE = new ArrayList<>();
        ResultSet rset = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM texto_corrido WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id_aula);
            rset = pstm.executeQuery();




            while (rset.next()) {
                TextoCorrido texto = new TextoCorrido(rset.getInt("id"), rset.getString("texto_corrido"), rset.getInt("id_aula"));
                liTE.add(texto);
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
            return liTE;
        }
    }


}
