package com.zeta_servlet.daos;

import com.zeta_servlet.daos.JDBC.Conexao;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.CRUD.CRUD;
import com.zeta_servlet.model.Aula;
import com.zeta_servlet.model.Lei;
import com.zeta_servlet.model.FlashCard;
import com.zeta_servlet.model.TextoCorrido;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO extends CRUD{

//    insere aulas na tabela
    public int inserir(Aula aula) {
        Connection conn = null;
        Conexao conexao = new Conexao();
        int out=0;
        try {
            conn = conexao.conectar(); // abre a conexão com o banco
            String consulta = "insert into aula(nome, id_modulo) values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            //Setando valores dos parametros
            pstmt.setString(1, aula.getNome());
            pstmt.setInt(2, aula.getIdModulo());


            // querys dos outros valores

            if (pstmt.executeUpdate()>0){
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


//  altera o nome de uma aula
    public int updateNome(Aula aula, String nome) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE aula SET nome = ? WHERE id = ?;");
            pstm.setString(1, nome);
            pstm.setInt(2, aula.getId());
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
            conexao.desconectar(coon);
        }
    }

    public int updateModulo(Aula aula, int id) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstm = coon.prepareStatement("UPDATE aula SET id_modulo = ? WHERE id = ?;");
            pstm.setInt(1, id);
            pstm.setInt(2, aula.getId());
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
            conexao.desconectar(coon);
        }
    }

//    remove uma aula da tabela, juntamente com as tabelas relacionadas
    public boolean remover(int id) {
        Conexao conexao = new Conexao();
        Connection coon = conexao.conectar();
        try {
            PreparedStatement pstmL = coon.prepareStatement("delete from lei where id_aula = ?");
            PreparedStatement pstmF = coon.prepareStatement("delete from flash_card where id_aula = ?");
            PreparedStatement pstmT = coon.prepareStatement("delete from texto_corrido where id_aula = ?");
            PreparedStatement pstmA = coon.prepareStatement("delete from aula where id = ?");

            pstmL.setInt(1, id);
            pstmF.setInt(1, id);
            pstmT.setInt(1, id);
            pstmA.setInt(1, id);

            if (pstmL.executeUpdate()>0 && pstmF.executeUpdate()>0 && pstmT.executeUpdate()>0 && pstmA.executeUpdate()>0){
                return true;
            }
            return false;
        }
        catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return false;
        }
        finally {
            conexao.desconectar(coon);
        }

    }

//    seleciona todos os itens da tabela
    public List<Aula> buscar() {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<FlashCard> liF = new ArrayList<>();
        List<TextoCorrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();

        ResultSet rsetA = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

                String busca = "select DISTINCT * from aula a join texto_corrido tc on tc.id_aula = a.id join lei l on l.id_aula = a.id join flash_card fc on fc.id_aula = a.id";
            PreparedStatement pstm = conn.prepareStatement(busca);
            rsetA = pstm.executeQuery();
            while (rsetA.next()) {

                    FlashCard flash = new FlashCard(rsetA.getInt("id"), rsetA.getString("frente"), rsetA.getString("verso"), rsetA.getInt("id_modulo"));
                    liF.add(flash);

                    Lei lei = new Lei(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                    liL.add(lei);

                    TextoCorrido texto = new TextoCorrido(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                    liT.add(texto);

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("nome"), rsetA.getInt("id_modulo"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }


//    seleciona uma aula com base no ID
    public List<Aula> buscarPorId(int id) {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<FlashCard> liF = new ArrayList<>();
        List<TextoCorrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();
        ResultSet rsetA = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from aula a join texto_corrido tc on tc.id_aula = a.id join lei l on l.id_aula = a.id join flash_card fc on fc.id_aula = a.id where a.id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rsetA = pstm.executeQuery();


            while (rsetA.next()) {

                FlashCard flash = new FlashCard(rsetA.getInt("id"), rsetA.getString("frente"), rsetA.getString("verso"), rsetA.getInt("id_modulo"));
                liF.add(flash);

                Lei lei = new Lei(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                liL.add(lei);

                TextoCorrido texto = new TextoCorrido(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                liT.add(texto);

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("nome"), rsetA.getInt("id_modulo"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }

//    busca a aula com base no nome
    public List<Aula> buscarPorNome(String nome) {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<FlashCard> liF = new ArrayList<>();
        List<TextoCorrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();
        ResultSet rsetA = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from aula a join texto_corrido tc on tc.id_aula = a.id join lei l on l.id_aula = a.id join flash_card fc on fc.id_aula = a.id where a.nome = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setString(1, nome);
            rsetA = pstm.executeQuery();


            while (rsetA.next()) {

                FlashCard flash = new FlashCard(rsetA.getInt("id"), rsetA.getString("frente"), rsetA.getString("verso"), rsetA.getInt("id_modulo"));
                liF.add(flash);

                Lei lei = new Lei(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                liL.add(lei);

                TextoCorrido texto = new TextoCorrido(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                liT.add(texto);

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("nome"), rsetA.getInt("id_modulo"), liT, liF, liL);
                liAU.add(aula);


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
            return liAU;
        }
    }


//    busca a aula com base no módulo

    public List<Aula> buscarPorIdModulo(int id_modulo) {
        //query
        List<Aula> liAU = new ArrayList<>();
        List<FlashCard> liF = new ArrayList<>();
        List<TextoCorrido> liT = new ArrayList<>();
        List<Lei> liL = new ArrayList<>();
        ResultSet rsetA = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from aula a join texto_corrido tc on tc.id_aula = a.id join lei l on l.id_aula = a.id join flash_card fc on fc.id_aula = a.id where a.id_modulo = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id_modulo);
            rsetA = pstm.executeQuery();


            while (rsetA.next()) {

                FlashCard flash = new FlashCard(rsetA.getInt("id"), rsetA.getString("frente"), rsetA.getString("verso"), rsetA.getInt("id_modulo"));
                liF.add(flash);

                Lei lei = new Lei(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                liL.add(lei);

                TextoCorrido texto = new TextoCorrido(rsetA.getInt("id"), rsetA.getString("lei"), rsetA.getInt("id_aula"));
                liT.add(texto);

                Aula aula = new Aula(rsetA.getInt("id"), rsetA.getString("nome"), rsetA.getInt("id_modulo"), liT, liF, liL);
                liAU.add(aula);
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
            return liAU;
        }
    }

    public int buscarUltimoId() {
        //query
        ResultSet rsetA = null;
        int id=-1;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from aula a order by id desc";
            PreparedStatement pstm = conn.prepareStatement(busca);
            rsetA = pstm.executeQuery();
            rsetA.next();
            id = rsetA.getInt("id");



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
            return id;
        }
    }

}
