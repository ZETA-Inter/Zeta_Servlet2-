package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Produtor;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class ProdutorDAO extends CRUD {

//    adiciona um produtor na tabela
    public int inserir(Produtor produtor) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {
            PreparedStatement pstmt = conn.prepareStatement("Insert into produtor(cpf, dt_nascimento, email, senha, pontos_acumulados, nome_primeiro, nome_ultimo, aulas_feitas) values (?, ? , ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, produtor.getCpf());
            pstmt.setObject(2, produtor.getDt_nascimento());
            pstmt.setString(3, produtor.getEmail());
            pstmt.setString(4, produtor.getSenha());
            pstmt.setInt(5, produtor.getPontos_acumulados());
            pstmt.setString(6, produtor.getNome_primeiro());
            pstmt.setString(7, produtor.getNome_ultimo());
            pstmt.setInt(8, produtor.getAulas_feitas());

            if (pstmt.executeUpdate() > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException se) {
            ExceptionHandler eh = new ExceptionHandler(se);
            eh.printExeption();
            return -1;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }

//    altera a senha de um produtor
    public int updateSenha(Produtor pcd, String senha) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstmt = conn.prepareStatement("UPDATE PRODUTOR SET SENHA = ? WHERE ID = ?");
            pstmt.setString(1, senha);
            pstmt.setInt(2, pcd.getId());


                if (pstmt.executeUpdate() > 0) {
                    return 1;
                } else {
                    return 0;
                }

        } catch (SQLException e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

//    altera o email de um produtor
    public int updateEmail(Produtor pcd, String email) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstmt = conn.prepareStatement("UPDATE PRODUTOR SET EMAIL = ? WHERE ID = ?");
            pstmt.setString(1, email);
            pstmt.setInt(2, pcd.getId());


                if (pstmt.executeUpdate() > 0) {
                    return 1;
                } else {
                    return 0;
                }

        } catch (SQLException e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

//    altera o primeiro nome do produtor
    public int alterarPrimeiroNome(Produtor pcd, String nome_primeiro){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try{

            PreparedStatement pstmt = conn.prepareStatement("UPDATE PRODUTOR SET NOME_PRIMEIRO = ? WHERE ID = ?");

            pstmt.setString(1, nome_primeiro);
            pstmt.setInt(2, pcd.getId());

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            else {
                return 0;
            }

        }
        catch (SQLException e){
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
            conexao.desconectar(conn);
        }
    }

//    altera o sobrenome do produtor
    public int alterarUltimoNome(String novo_nome_ultimo,  int idProdutor){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try{

            PreparedStatement pstmt = conn.prepareStatement("UPDATE PRODUTOR SET NOME_ULTIMO = ? WHERE ID = ?");

            pstmt.setString(1, novo_nome_ultimo);
            pstmt.setInt(2, idProdutor);

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            else {
                return 0;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
        finally {
            conexao.desconectar(conn);
        }
    }
//  remove um produtor
    public boolean remover(int id) {return super.remover(id, "produtor");}

//  seleciona todos os produtores da tabela
    public List<Produtor> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();
        try {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR ORDER BY ID");

            ResultSet rset = pstmt.executeQuery();


            while(rset.next()) {
            listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }


        }

        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }

    }

//    seleciona um produtor pelo ID
    public List<Produtor> buscarPorID(int id){
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            List<Produtor> listaProdt = new ArrayList<>();

        try{

                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE ID = ?");

                pstmt.setInt(1, id);
                ResultSet rset = pstmt.executeQuery();
                while(rset.next()) {
                    listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
                }
            }

            catch(SQLException e){
                ExceptionHandler eh = new ExceptionHandler(e);
                eh.printExeption();
            }
            catch (Exception e){
                ExceptionHandler eh = new ExceptionHandler(e);
                eh.printExeption();
            }
            finally {
                conexao.desconectar(conn);
                return listaProdt;
            }
    }

//    seleciona o produtor com base no CPF
    public List<Produtor> buscarPorCPF(String cpf){
        List<Produtor> listaProdt = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try{

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE CPF = ?");
            pstmt.setString(1, cpf);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }

        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }

    }

//    seleciona
    public List<Produtor> buscarPorPontosAcumulados(int pontos_acumulados){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();
        try{

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE PONTOS_ACUMULADOS = ?");
            pstmt.setInt(1, pontos_acumulados);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }

        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }
    }

    public List<Produtor> buscarPorPrimeiroNome(String nome_primeiro){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE NOME_PRIMEIRO = ?");
            pstmt.setString(1, nome_primeiro);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }

        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }

    }

    public List<Produtor> buscaPorUltimoNome(String nome_ultimo){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();

        try {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE NOME_ULTIMO = ?");
            pstmt.setString(1, nome_ultimo);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }

        }

        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }

    }

    public List<Produtor> buscaPorAulasFeitas(int aulasFeitas){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();

        try {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE AULAS_FEITAS = ?");
            pstmt.setInt(1, aulasFeitas);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }

        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }
    }

    public List<Produtor> buscarPorIdFornecedor(int idFornecedor){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();

        try{

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE ID_FORNECEDOR = ?");
            pstmt.setInt(1, idFornecedor);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }


        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return listaProdt;
        }

    }

    public List<Produtor> buscarPorIdAssinatura(int IdAssinatura){
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Produtor> listaProdt = new ArrayList<>();

        try{

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUTOR WHERE ID_ASSINATURA = ?");
            pstmt.setInt(1, IdAssinatura);

            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                listaProdt.add(new Produtor(rset.getInt("ID"), rset.getString("CPF"), rset.getObject("DT_NASCIMENTO", LocalDate.class), rset.getString("EMAIL"), rset.getString("SENHA"),rset.getInt("PONTOS_ACUMULADOS"), rset.getString("NOME_PRIMEIRO"),rset.getString("NOME_ULTIMO"), rset.getInt("AULAS_FEITAS"), rset.getInt("ID_FORNECEDOR"), rset.getInt("ID_ASSINATURA")));
            }


        }
        catch (SQLException e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {

            conexao.desconectar(conn);
            return listaProdt;
        }
    }
}
