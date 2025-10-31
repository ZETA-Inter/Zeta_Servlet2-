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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtividadeDAO extends CRUD{
//    insere um item na tabela
public int inserir(Atividade atv) {
    Connection conn = null;
    Conexao conexao = new Conexao();

    try {
        conn = conexao.conectar();

        // 1. INSERIR ATIVIDADE (com retorno do ID)
        String consultaAtividade = "insert into Atividade(pontuacao, id_aula) values(?, ?)";
        PreparedStatement pstmtAtividade = conn.prepareStatement(consultaAtividade, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmtAtividade.setDouble(1, atv.getPontuacao());
        pstmtAtividade.setInt(2, atv.getId_aula());
        pstmtAtividade.executeUpdate();

        // 2. PEGAR ID GERADO
        int idAtividade;
        try (ResultSet generatedKeys = pstmtAtividade.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                idAtividade = generatedKeys.getInt(1);
                System.out.println("ID da atividade gerado: " + idAtividade);
            } else {
                throw new SQLException("Falha ao obter ID da atividade");
            }
        }

        // 3. INSERIR PERGUNTAS
        if (atv.getPerguntas() != null && !atv.getPerguntas().isEmpty()) {
            String consultaPergunta = "insert into pergunta(pergunta, id_atividade) values(?, ?)";
            PreparedStatement pstmtPerg = conn.prepareStatement(consultaPergunta);

            for (Pergunta pergunta : atv.getPerguntas()) {
                pstmtPerg.setString(1, pergunta.getPergunta());
                pstmtPerg.setInt(2, idAtividade); // Usa o ID gerado
                pstmtPerg.executeUpdate(); // ← NÃO ESQUEÇA DESTA LINHA!
                System.out.println("Pergunta inserida: " + pergunta.getPergunta());
            }
        }

        // 4. INSERIR ALTERNATIVAS (nome da coluna corrigido)
        if (atv.getAlternativas() != null && !atv.getAlternativas().isEmpty()) {
            String consultaAlternativa = "insert into alternativa(alternativa, id_atividade, correta) values(?, ?, ?)";
            PreparedStatement pstmtAlt = conn.prepareStatement(consultaAlternativa);

            for (Alternativa alternativa : atv.getAlternativas()) {
                pstmtAlt.setString(1, alternativa.getAlternativa());
                pstmtAlt.setInt(2, idAtividade); // Usa o ID gerado
                pstmtAlt.setBoolean(3, alternativa.isCorreto());
                pstmtAlt.executeUpdate(); // ← NÃO ESQUEÇA DESTA LINHA!
                System.out.println("Alternativa inserida: " + alternativa.getAlternativa() + " - Correta: " + alternativa.isCorreto());
            }
        }

        System.out.println("Atividade completa inserida com sucesso! ID: " + idAtividade);
        return idAtividade; // Retorna o ID em vez de 1

    } catch (SQLException e) {
        System.out.println("ERRO SQL: " + e.getMessage());
        e.printStackTrace();
        return -1;
    } catch (Exception e) {
        System.out.println("ERRO: " + e.getMessage());
        e.printStackTrace();
        return -1;
    } finally {
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


//    remove uma atividade, juntamente das tabelas relacionadas
public boolean remover(int id) {
    Conexao conexao = new Conexao();
    Connection coon = conexao.conectar();
    try {
        PreparedStatement pstmAL = coon.prepareStatement("delete from alternativa where id_atividade = ?");
        PreparedStatement pstmP = coon.prepareStatement("delete from pergunta where id_atividade = ?");
        PreparedStatement pstmAT = coon.prepareStatement("delete from atividade where id = ?");

        pstmAL.setInt(1, id);
        pstmP.setInt(1, id);
        pstmAT.setInt(1, id);

        if (pstmAT.executeUpdate()>0 && pstmP.executeUpdate()>0 && pstmAL.executeUpdate()>0){
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

//    seleciona todas as atividades da tabela
public List<Atividade> buscar() {
    List<Atividade> liAT = new ArrayList<>();
    Map<Integer, Atividade> atividadesMap = new HashMap<>();
    ResultSet rsetAT = null;
    Conexao conexao = new Conexao();
    Connection conn = conexao.conectar();

    String sql = "select a.id, a.pontuacao, p.pergunta, al.alternativa, al.correta, a.id_aula, " +
            "p.id as id_pergunta, al.id as id_alternativa, p.id_atividade, al.id_atividade " +
            "from atividade a " +
            "join alternativa al on al.id_atividade = a.id " +
            "join pergunta p on p.id_atividade = a.id";

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        rsetAT = stmt.executeQuery();

        while (rsetAT.next()) {
            int idAtividade = rsetAT.getInt("id");

            Atividade atividade = atividadesMap.get(idAtividade);
            if (atividade == null) {
                // Cria nova atividade com listas VAZIAS
                atividade = new Atividade(
                        idAtividade,
                        rsetAT.getDouble("pontuacao"),
                        rsetAT.getInt("id_aula"),
                        new ArrayList<>(),  // Lista vazia para perguntas
                        new ArrayList<>()   // Lista vazia para alternativas
                );

                atividadesMap.put(idAtividade, atividade);
                liAT.add(atividade);
            }

            // Adiciona alternativa à atividade específica (se não existir)
            String textoAlternativa = rsetAT.getString("alternativa");
            if (textoAlternativa != null && !contemAlternativa(atividade.getAlternativas(), textoAlternativa)) {
                Alternativa alternativa = new Alternativa(
                        rsetAT.getInt("id_alternativa"),
                        textoAlternativa,
                        rsetAT.getInt("id_atividade"),
                        rsetAT.getBoolean("correta")
                );
                atividade.getAlternativas().add(alternativa);
            }

            // Adiciona pergunta à atividade específica (se não existir)
            String textoPergunta = rsetAT.getString("pergunta");
            if (textoPergunta != null && !contemPergunta(atividade.getPerguntas(), textoPergunta)) {
                Pergunta pergunta = new Pergunta(
                        rsetAT.getInt("id_pergunta"),
                        textoPergunta,
                        rsetAT.getInt("id_atividade")
                );
                atividade.getPerguntas().add(pergunta);
            }
        }

    } catch (SQLException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e) {
        ExceptionHandler eh = new ExceptionHandler(e);
        eh.printExeption();
    } catch (Exception e) {
        ExceptionHandler eh = new ExceptionHandler(e);
        eh.printExeption();
    } finally {
        if (rsetAT != null) {
            try {
                rsetAT.close();
            } catch (SQLException e) {
                ExceptionHandler eh = new ExceptionHandler(e);
                eh.printExeption();
            }
        }
        conexao.desconectar(conn);
    }

    return liAT;
}

    private boolean contemPergunta(List<Pergunta> perguntas, String texto) {
        if (perguntas == null || texto == null) return false;
        return perguntas.stream().anyMatch(p -> p.getPergunta() != null && p.getPergunta().equals(texto));
    }

    private boolean contemAlternativa(List<Alternativa> alternativas, String texto) {
        if (alternativas == null || texto == null) return false;
        return alternativas.stream().anyMatch(a -> a.getAlternativa() != null && a.getAlternativa().equals(texto));
    }

//    busca uma atividade por ID

    public List<Atividade> buscarPorId(int id) {
        //query
        List<Atividade> liAT = new ArrayList<>();
        List<Alternativa> liAL = new ArrayList<>();
        List<Pergunta> liP = new ArrayList<>();

        ResultSet rsetAT = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from atividade a join alternativa al on al.id_atividade = a.id join pergunta p on p.id_atividade = a.id where a.id = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, id);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()){
                Alternativa alternativa = new Alternativa(rsetAT.getInt("id"), rsetAT.getString("alternativa"), rsetAT.getInt("id_atividade"), rsetAT.getBoolean("correta"));
                liAL.add(alternativa);
                Pergunta pergunta = new Pergunta(rsetAT.getInt("id"), rsetAT.getString("pergunta"), rsetAT.getInt("id_atividade"));
                liP.add(pergunta);
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), liP, liAL);
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

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from atividade a join alternativa al on al.id_atividade = a.id join pergunta p on p.id_atividade = a.id where a.pontuacao = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setDouble(1, pontuacao);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()){
                Alternativa alternativa = new Alternativa(rsetAT.getInt("id"), rsetAT.getString("alternativa"), rsetAT.getInt("id_atividade"), rsetAT.getBoolean("correta"));
                liAL.add(alternativa);
                Pergunta pergunta = new Pergunta(rsetAT.getInt("id"), rsetAT.getString("pergunta"), rsetAT.getInt("id_atividade"));
                liP.add(pergunta);
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), liP, liAL);
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
            String busca = "select * from atividade a join alternativa al on al.id_atividade = a.id join pergunta p on p.id_atividade = a.id where a.id_aula = ?";
            PreparedStatement pstm = conn.prepareStatement(busca);
            pstm.setInt(1, idAula);
            rsetAT = pstm.executeQuery();


            while (rsetAT.next()){
                Alternativa alternativa = new Alternativa(rsetAT.getInt("id"), rsetAT.getString("alternativa"), rsetAT.getInt("id_atividade"), rsetAT.getBoolean("correta"));
                liAL.add(alternativa);
                Pergunta pergunta = new Pergunta(rsetAT.getInt("id"), rsetAT.getString("pergunta"), rsetAT.getInt("id_atividade"));
                liP.add(pergunta);
                Atividade atividade = new Atividade(rsetAT.getInt("id"), rsetAT.getDouble("pontuacao"), rsetAT.getInt("id_aula"), liP, liAL);
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

    public int buscarUltimoId() {
        //query
        ResultSet rsetA = null;
        int id=-1;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String busca = "select * from atividade a order by id desc";
            PreparedStatement pstm = conn.prepareStatement(busca);
            rsetA = pstm.executeQuery();
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
