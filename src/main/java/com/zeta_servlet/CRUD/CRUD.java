package com.zeta_servlet.CRUD;


import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;

import java.sql.*;
// classe abstrata com a base dos métodos de CRUD
public abstract class CRUD {


    // método remover
    public boolean remover(int id, String table) {
//        estabelecendo conexão com BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
//          cria uma variável da query que será executada
            String remover = "DELETE FROM "+table+" WHERE id = ?";
//            prepara a query para ser executada
            PreparedStatement pstm = conn.prepareStatement(remover);
//            substituí o parâmetro variável na query pelo parâmetro do método
            pstm.setInt(1, id);
//            executa a query e verifica se a query funcionou
            if (pstm.executeUpdate()>0){
                return true;
            }
            return false;
        }
//        método retorna false se tiver uma exceção
        catch (SQLException e) {
            e.printStackTrace ();
            return false;
        }
//        finaliza a conexão com banco após a execução dos outros blocos
        finally {
            conexao.desconectar(conn);
        }
    }

//    busca um item na tabela com base no ID
    public ResultSet buscarPorIdR(int id, String table) {
//        cria uma variável result set que será o retorno do método
        ResultSet rset=null;
//        cria a coxeão com o BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
//            cria e prepara a query
            Statement stmt = conn.createStatement();
            String busca = "SELECT * FROM "+table+" WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
//            substitui o valor na query pelo parâmetro da variável
            pstm.setInt(1,id);
//            executa a query
            rset=pstm.executeQuery();

        }
//        trata a exceção de BD
        catch (SQLException se) {
            se.printStackTrace();
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(conn);
        }
//        retorna null se o método não funcionar ou não encontrar nenhum item no BD ou retorna o item da tabela
        return rset;
    }

//    faz um select na tabela toda
    public ResultSet buscarR(String table) throws SQLException,  NullPointerException, IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException{
//        cria uma variável result set que será o retorno do método
        ResultSet rset=null;
//        cria a coxeão com o BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
//            cria e executa a query
            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM "+table+" ORDER BY id");

//        trata alguma exceção que aconteceu que não foi tratada no throws
        } catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        //        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(conn);
        }
//        retorna null se o método não funcionar ou não encontrar nenhum item no BD ou retorna o item da tabela

        return rset;
    }

}
