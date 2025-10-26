package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.Atividade;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Pergunta;
import com.zeta_servlet.model.Alternativa;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtividadeDAO extends CRUD{

//    insere um item na tabela
    public int inserir(Atividade atv) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        try {

            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into Atividade(pontuacao, id_aula, fk_imagens_url) values(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setDouble(1, atv.getPontuacao());
            pstmt.setInt(2, atv.getId_aula());
            pstmt.setString(3, atv.getImagens_url());

            // querys dos outros valores

            //query alternativas
            for (int i = 0; i < atv.getAlternativas().size(); i++) {
                String consultaFlash = "insert into alternativa(alternativa, id_ativade, correto) values(?, ?, ?)";
                PreparedStatement pstmtFlash = conn.prepareStatement(consultaFlash);
                pstmt.setString(1, atv.getAlternativas().get(i).getAlternativa());
                pstmt.setInt(2, atv.getAlternativas().get(i).getId_atividade());
                pstmt.setBoolean(3, atv.getAlternativas().get(i).isCorreto());
            }

            //query pergunta
            for (int i = 0; i < atv.getPerguntas().size(); i++) {
                String consultaTexto = "insert into pergunta(pergunta, id_atividade) values(?, ?)";
                PreparedStatement pstmtFlash = conn.prepareStatement(consultaTexto);
                pstmt.setString(1, atv.getPerguntas().get(i).getPergunta());
                pstmt.setInt(2, atv.getPerguntas().get(i).getId_atividade());
            }

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

    public int updatePontuacao(Atividade atv, double pontucao) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE atividade SET pontuacao = ? WHERE id = ?;");
            pstm.setDouble(1, pontucao);
            pstm.setInt(2, atv.getId());
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

//    altera a imagem da atividade
    public int updateImagensUrl(Atividade atv, String url) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE atividade SET fk_Imagens_url = ? WHERE id = ?;");
            pstm.setString(1, url);
            pstm.setInt(2, atv.getId());
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
    public boolean remover(int id) {return super.remover(id, "atividade");}

//    seleciona todas as atividades da tabela
    public List<Atividade> buscar() {
        //query
        List<Atividade> liAT = new ArrayList<>();
        List<Alternativa> liAL = new ArrayList<>();
        List<Pergunta> liP = new ArrayList<>();

        ResultSet rsetAT = null;
        ResultSet rsetAL = null;
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            rsetAT = buscarR("atividade");

            while (rsetAT.next()) {
                int id = rsetAT.getInt("id");

                rsetAL = buscarPorIdR(id, "alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
                }

                rsetP = buscarPorIdR(id, "pergunta");
                while (rsetP.next()){
                    Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                    liP.add(pergunta);
                }
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), rsetAT.getString("fk_Imagens_url"), liP, liAL);
                liAT.add(atividade);
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
            return liAT;
        }
    }

//    busca uma atividade por ID

    public List<Atividade> buscarPorId(int id) {
        //query
        List<Atividade> liAT = new ArrayList<>();
        List<Alternativa> liAL = new ArrayList<>();
        List<Pergunta> liP = new ArrayList<>();

        ResultSet rsetAT = null;
        ResultSet rsetAL = null;
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM atividade WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()) {
                rsetAL = buscarPorIdR(id, "alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
                }

                rsetP = buscarPorIdR(id, "pergunta");
                while (rsetP.next()){
                    Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                    liP.add(pergunta);
                }
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), rsetAT.getString("fk_Imagens_url"), liP, liAL);
                liAT.add(atividade);
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
            return liAT;
        }
    }


//    busca atividades com base na pontuação

    public List<Atividade> buscarPorPontuacao(double pontuacao) {
        //query
        List<Atividade> liAT = new ArrayList<>();
        List<Alternativa> liAL = new ArrayList<>();
        List<Pergunta> liP = new ArrayList<>();

        ResultSet rsetAT = null;
        ResultSet rsetAL = null;
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM atividade WHERE pontuacao = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setDouble(1, pontuacao);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()) {
                int id = rsetAT.getInt("id");

                rsetAL = buscarPorIdR(id, "alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
                }

                rsetP = buscarPorIdR(id, "pergunta");
                while (rsetP.next()){
                    Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                    liP.add(pergunta);
                }
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), rsetAT.getString("fk_Imagens_url"), liP, liAL);
                liAT.add(atividade);
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
            return liAT;
        }
    }


//    busca atividade com base na aula

    public List<Atividade> buscarPorIdAula(int idAula) {
        //query
        List<Atividade> liAT = new ArrayList<>();
        List<Alternativa> liAL = new ArrayList<>();
        List<Pergunta> liP = new ArrayList<>();

        ResultSet rsetAT = null;
        ResultSet rsetAL = null;
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM atividade WHERE id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, idAula);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()) {
                int id = rsetAT.getInt("id");

                rsetAL = buscarPorIdR(id, "alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
                }

                rsetP = buscarPorIdR(id, "pergunta");
                while (rsetP.next()){
                    Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                    liP.add(pergunta);
                }
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), rsetAT.getString("fk_Imagens_url"), liP, liAL);
                liAT.add(atividade);
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
            return liAT;
        }
    }

//    busca atividades com base na imagem
    public List<Atividade> buscarPorImagemURl(String imagemURL) {
        //query
        List<Atividade> liAT = new ArrayList<>();
        List<Alternativa> liAL = new ArrayList<>();
        List<Pergunta> liP = new ArrayList<>();

        ResultSet rsetAT = null;
        ResultSet rsetAL = null;
        ResultSet rsetP = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "SELECT * FROM atividade WHERE fk_Imagens_url = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, imagemURL);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()) {
                int id = rsetAT.getInt("id");

                rsetAL = buscarPorIdR(id, "alternativa");
                while (rsetAL.next()) {
                    Alternativa alternativa = new Alternativa(rsetAL.getInt("id"), rsetAL.getString("alternativa"), rsetAL.getInt("id_atividade"), rsetAL.getBoolean("correto"));
                    liAL.add(alternativa);
                }

                rsetP = buscarPorIdR(id, "pergunta");
                while (rsetP.next()){
                    Pergunta pergunta = new Pergunta(rsetP.getInt("id"), rsetP.getString("pergunta"), rsetP.getInt("id_atividade"));
                    liP.add(pergunta);
                }
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), rsetAT.getString("fk_Imagens_url"), liP, liAL);
                liAT.add(atividade);
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
            return liAT;
        }
    }
}
