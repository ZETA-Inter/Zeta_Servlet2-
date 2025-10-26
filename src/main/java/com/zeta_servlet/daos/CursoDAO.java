package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO extends CRUD {

//    insere um curso na tabela
    public int inserir(Curso curso) {
        Connection conn = null;
        Conexao conexao = new Conexao();

        try {

            conn = conexao.conectar(); // abre a conexão com o banco

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CURSO (NOME, SEGMENTO, DESCRICAO) VALUES(?, ?, ?)");

            //Setando valores dos parametros

            pstmt.setString(1, curso.getNome());
            pstmt.setString(2, curso.getSegmento());
            pstmt.setString(3, curso.getDescricao());

            if (pstmt.executeUpdate() >0){
                return 1;
            }
            return 0;
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

//    altera o nome de um curso
    public int updateNome(Curso curso, String novoNome) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("UPDATE CURSO SET NOME = ? WHERE ID = ?");
            pstm.setString(1, novoNome);
            pstm.setInt(2, curso.getId());

            if (pstm.executeUpdate()>0){
                return 1;

            }

            return 0;
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


//    altera o segmento do curso
    public int updateSegmento(Curso curso, String novoSegmento) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("UPDATE CURSO SET SEGMENTO = ? WHERE ID = ?");
            pstm.setString(1, novoSegmento);
            pstm.setInt(2, curso.getId());

            if (pstm.executeUpdate()>0){
                return 1;

            }

            return 0;

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

//    altera a descrição da aula
    public int updateDescricao(Curso curso, String novaDescricao) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("UPDATE CURSO SET DESCRICAO = ? WHERE ID = ?");
            pstm.setString(1, novaDescricao);
            pstm.setInt(2, curso.getId());

            if (pstm.executeUpdate()>0){
                return 1;

            }

            return 0;

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

//    remove um curso na tabela
    public boolean remover(int id) {return super.remover(id, "curso");}

//    seleciona todos os cursos da tabela
    public List<Curso> buscar() {
        //query
        List<Curso> liCurso = new ArrayList<>();
        ResultSet rset = null;

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            rset = buscarR("curso");

            while (rset.next()) {
                Curso curso = new Curso(rset.getInt("id"), rset.getString("nome"), rset.getString("segmento"), rset.getString("descricao"));
                liCurso.add(curso);
            }

            return liCurso;

        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return liCurso;
        }

        finally {
            conexao.desconectar(conn);
        }
    }


//    seleciona o curso com base no ID
    public List<Curso> buscarPorId(int id) {
        //query
        List<Curso> liCurso = new ArrayList<>();
        ResultSet rset = null;

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM CURSO WHERE ID = ?");
            pstm.setInt(1, id);
            rset = pstm.executeQuery();


            while (rset.next()) {
                Curso curso = new Curso(rset.getInt("id"), rset.getString("nome"), rset.getString("segmento"), rset.getString("descricao"));
                liCurso.add(curso);
            }

            return liCurso;

        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return liCurso;
        }

        finally {
            conexao.desconectar(conn);
        }
    }

//    busca o curso com base no nome
    public List<Curso> buscarPorNome(String nome) {

        //query
        List<Curso> liCurso = new ArrayList<>();
        ResultSet rset = null;

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM CURSO WHERE NOME = ?");
            pstm.setString(1, nome);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Curso curso = new Curso(rset.getInt("id"), rset.getString("nome"), rset.getString("segmento"), rset.getString("descricao"));
                liCurso.add(curso);
            }

            return liCurso;

        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return liCurso;
        }

        finally {
            conexao.desconectar(conn);
        }
    }

//    busca o curso com base no segmento
    public List<Curso> buscarPorSegmento(String segmento) {

        //query
        List<Curso> liCurso = new ArrayList<>();
        ResultSet rset = null;

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM CURSO WHERE SEGMENTO = ?");
            pstm.setString(1, segmento);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Curso curso = new Curso(rset.getInt("id"), rset.getString("nome"), rset.getString("segmento"), rset.getString("descricao"));
                liCurso.add(curso);
            }

            return liCurso;

        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return liCurso;
        }

        finally {
            conexao.desconectar(conn);
        }
    }


//    busca o curso com base na descrição
    public List<Curso> buscarPorDescricao(String descricao) {

        //query
        List<Curso> liCurso = new ArrayList<>();
        ResultSet rset = null;

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM CURSO WHERE DESCRICAO = ?");
            pstm.setString(1, descricao);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Curso curso = new Curso(rset.getInt("id"), rset.getString("nome"), rset.getString("segmento"), rset.getString("descricao"));
                liCurso.add(curso);
            }

            return liCurso;
        }

        catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return liCurso;
        }

        finally {
            conexao.desconectar(conn);
        }
    }
}