package com.zeta_servlet.daos;
import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Adm;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdmDAO extends CRUD {

// insere um item na tabela ADM
    public int inserir(Adm adm) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {
        // abre a conexão com o banco
            conn = conexao.conectar();
//            cria e prepara a query
            String consulta = "insert into adm(email, senha) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, adm.getEmail());
            pstmt.setString(2, adm.getSenha());
//            retorna 1 se a query for executada fizer uma alteração no banco ou 0 se a query for executada mas não alterar o banco
            if (pstmt.executeUpdate() >0){
                return 1;
            }
            return 0;
        }
//        trata exceções possíveis e retorna -1, pois a query não foi executada com sucesso
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
//        trata exceções não tratadas no bloco anterior e retorna a exceção e -1
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(conn);
        }
    }




    // muda a senha de um adm
    public int updateSenha(Adm adm, String senha) {
//        criando conexão com o BD
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
//            cria e prepara a query
            PreparedStatement pstm = coon.prepareStatement("UPDATE adm SET senha = ? WHERE id = ?;");
//            setando valores
            pstm.setString(1, senha);
            pstm.setInt(2, adm.getId());
//            retorna 1 se a query for executada fizer uma alteração no banco ou 0 se a query for executada mas não alterar o banco
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
//        trata exceções relacionadas a banco de dados e retorna -1, pois a query não foi executada com sucesso

        catch (SQLException e){
            e.printStackTrace ();
            return -1;
        }
//        trata exceções não tratadas no bloco anterior e retorna a exceção e -1
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(coon);
        }
    }

    // muda o email de um item da tabela adm
    public int updateEmail(Adm adm, String email) {
        // abre a conexão com o banco
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
//            cria e prepara a query
            PreparedStatement pstm = coon.prepareStatement("UPDATE adm SET email = ? WHERE id = ?;");
//            setando valores
            pstm.setString(1, email);
            pstm.setInt(2, adm.getId());
//            retorna 1 se a query for executada fizer uma alteração no banco ou 0 se a query for executada mas não alterar o banco
            if (pstm.executeUpdate()>0){
                return 1;

            }  return 0;
        }
//        trata exceções relacionadas a banco de dados e retorna -1, pois a query não foi executada com sucesso
        catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
//        trata exceções não tratadas no bloco anterior e retorna a exceção e -1
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(coon);
        }
    }


//  remove um item da tabela ADM com base no ID
    public boolean remover(int id) {
//      o método retorna true ou false com base no retorno do método remover da classe CRUD
        return super.remover(id, "adm");
    }


//    retorna a tabela ADM inteira
    public List<Adm> buscar() {
//        cria uma lista que irá armazenar as linhas da consulta
        List<Adm> liADM = new ArrayList<>();
//        cria uma variável result set que será uma linha da consulta
        ResultSet rset = null;
//        abrindo conexão com o BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
//            usa o método da classe CRUD para fazer um select da tabela toda e atribui o resultado na variável
            rset = super.buscarR("Adm");
//            armazena todos as linhas da consulta em objetos da classe modelo e adiciona na lista
            while (rset.next()) {
                Adm adm = new Adm(rset.getString("email"), rset.getInt("id"), rset.getString("senha"));
                liADM.add(adm);
            }
        }
//      trata possíveis exceções e retorna a exceção e o que aconteceu
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace ();
        }
//      trata exceções não tratadas no bloco anterior
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(conn);
        }
//        retorna null se o método não funcionar ou não encontrar nenhum item no BD ou retorna os itens da tabela
        return liADM;
    }


//    busca um item da tabela com base no ID
    public List<Adm> buscarId(int id) {
//        cria uma variável result set que será o retorno do método
        ResultSet rset = null;
        List<Adm> liADM = new ArrayList<>();
//        cria a coxeão com o BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
//            cria e prepara a query
            String busca = "SELECT * FROM adm WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            //Setando valores dos parametros
            pstm.setInt(1, id);
//            executa a query
            rset = pstm.executeQuery();

            while (rset.next()) {
                Adm adm = new Adm(rset.getString("email"), rset.getInt("id"), rset.getString("senha"));
                liADM.add(adm);
            }
        }
//      trata possíveis exceções e retorna a exceção e o que aconteceu
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace ();
        }
//      trata exceções não tratadas no bloco anterior
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(conn);
        }
//      retorna null se a query não for executada ou se a query não retornar nenhum item da tabela ou retorna a linha da tabela
        return liADM;
    }

//  busca itens na tabela com base no email
    public List<Adm> buscarPorEmail(String email) {
//        cria uma lista que irá armazenar as linhas da consulta
        List<Adm> liADM = new ArrayList<>();
//        cria uma variável result set que será uma linha da consulta
        ResultSet rset = null;
//        abrindo conexão com o BD
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
//            cria e prepara a query
            String busca = "SELECT * FROM adm WHERE email = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
//            setando valores dos parametros
            pstm.setString(1, email);
//            executa a query e guarda os valores na variável
            rset = pstm.executeQuery();
//            armazena todos as linhas da consulta em objetos da classe modelo e adiciona na lista
            while (rset.next()) {
                Adm adm = new Adm(rset.getString("email"), rset.getInt("id"), rset.getString("senha"));
                liADM.add(adm);
            }
        }
//      trata possíveis exceções e retorna a exceção e o que aconteceu
        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace ();
        }
//      trata exceções não tratadas no bloco anterior
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
//        desconecta com o BD após a execução dos blocos anteriores
        finally {
            conexao.desconectar(conn);

        }
//        retorna null se o método não funcionar ou não encontrar nenhum item no BD ou retorna os itens da tabela
        return liADM;
    }

}
