package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO extends CRUD{

//    insere um fornecedor na tabela
    public int inserir(Fornecedor forc) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into fornecedor(idEmpresa, idFornecedor, nomePri, nomeUlt, email, senha, telefone) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setInt(1, forc.getIdEmpresa());
            pstmt.setInt(2, forc.getIdEmpresa());
            pstmt.setString(3,forc.getNomePri());
            pstmt.setString(4, forc.getNomeUlt());
            pstmt.setString(5, forc.getEmail());
            pstmt.setString(6, forc.getSenha());
            pstmt.setString(7, forc.getTelefone());
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


//    altera a empresa do fornecedor

    public int updateEmpresa(Fornecedor forc, int id_empresa) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE FORNECEDOR SET id_empresa = ? WHERE id = ?;");
            pstm.setInt(1, id_empresa);
            pstm.setInt(2, forc.getId());
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
            conexao.desconectar(conn);
        }
    }


//    altera a senha do fornecedor
    public int updateSenha(Fornecedor forc, String senha) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE FORNECEDOR SET senha = ? WHERE id = ?;");
            pstm.setString(1, senha);
            pstm.setInt(2, forc.getId());
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

//    altera a assinatura do fornecedor
    public int updateIdAssinatura(Fornecedor forc, int id_assinatura) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE FORNECEDOR SET id_assinatura = ? WHERE id = ?;");
            pstm.setInt(1, id_assinatura);
            pstm.setInt(2, forc.getId());
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

//    altera o email do fornecedor
    public int updateEmail(Fornecedor forc, String email) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE fornecedor SET email = ? WHERE id = ?;");
            pstm.setString(1, email);
            pstm.setInt(2, forc.getId());
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

//    verifica se o fornecedor existe na tabela
    public int verificarDados(Fornecedor forc){
        ResultSet rset=null;
        int id;
        String email;
        String senha;
        String nomePri;
        String nomeUlt;
        int idAssinatura;
        int idEmpresa;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM fornecedor ORDER BY id");
            senha=rset.getString("senha");
            email=rset.getString("email");
            id=rset.getInt("id");
            nomePri = rset.getString("nome_primeiro");
            nomeUlt = rset.getString("nome_ultimo");
            idAssinatura = rset.getInt("id_assinatura");
            idEmpresa = rset.getInt("id_empresa");

            if (forc.getEmail()==email && forc.getSenha()==senha && forc.getId()==id && forc.getIdAssinatura() == idAssinatura && forc.getIdEmpresa() == idEmpresa && forc.getNomeUlt() == nomeUlt && forc.getNomePri() == nomePri){
                return 1;
            }
            return 0;
        }catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

//  remove um fornecedor
    public boolean remover(int id) {return super.remover(id, "Fornecedor");}

//  seleciona todos os fornecedores da tabela

    public List<Fornecedor> buscar() {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rsetF = buscarR("Fornecedor");


            while (rsetF.next()) {
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("telefone"));
                liFOR.add(fornecedor);
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
            return liFOR;
        }
    }

//    busca o fornecedor com base no ID

    public
    List<Fornecedor> buscarId(int id) {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM fornecedor WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rsetF = pstm.executeQuery();

            while (rsetF.next()){
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("Telefone"));
                liFOR.add(fornecedor);}

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
            return liFOR;
        }
    }

//    busca o fornecedor com base no sobrenome

    public List<Fornecedor> buscarPorNomeUlt(String nome) {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM fornecedor WHERE nome_ultimo = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, nome);
            rsetF = pstm.executeQuery();


            while (rsetF.next()) {
                int idFornecedor = rsetF.getInt("id");



                while (rsetF.next()){
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("telefone"));
                liFOR.add(fornecedor);
                }
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
            return liFOR;
        }
    }

//    busca um fornecedor com base no email

    public List<Fornecedor> buscarPorEmail(String email) {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM fornecedor WHERE email = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, email);
            rsetF = pstm.executeQuery();

            while (rsetF.next()){
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("telefone"));
                liFOR.add(fornecedor);
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
            return liFOR;
        }
    }

//    busca fornecedores com base na assinatura

    public List<Fornecedor> buscarPorAssinatura(int assinatura) {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM fornecedor WHERE id_assinatura = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, assinatura);
            rsetF = pstm.executeQuery();


            while (rsetF.next()){
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("telefone"));
                liFOR.add(fornecedor);}

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
            return liFOR;
        }
    }

//    busca fornecedores com base na empresa

    public List<Fornecedor> buscarPorEmpresa(int empresa) {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM fornecedor WHERE id_empresa = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, empresa);
            rsetF = pstm.executeQuery();



            while (rsetF.next()){
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("telefone"));
                liFOR.add(fornecedor);}

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
            return liFOR;
        }
    }

//    busca fornecedores pelo primeiro nome

    public List<Fornecedor> buscarPorNomePrimeiro(String nome) {
        //query
        List<Fornecedor> liFOR = new ArrayList<>();
        ResultSet rsetF = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM fornecedor WHERE nome_primeiro = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, nome);
            rsetF = pstm.executeQuery();


            while (rsetF.next()){
                Fornecedor fornecedor = new Fornecedor(rsetF.getInt("id"), rsetF.getInt("id_empresa"), rsetF.getString("nome_primeiro"), rsetF.getInt("id_assinatura"), rsetF.getString("nome_ultimo"), rsetF.getString("email"), rsetF.getString("senha"), rsetF.getString("telefone"));
                liFOR.add(fornecedor);}

        }

        catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            conexao.desconectar(conn);
            return liFOR;
        }
    }

}
