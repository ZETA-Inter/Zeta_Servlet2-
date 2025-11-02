package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AlternativaDAO;
import com.zeta_servlet.daos.AtividadeDAO;
import com.zeta_servlet.daos.PerguntaDAO;
import com.zeta_servlet.model.Atividade;
import com.zeta_servlet.model.Pergunta;
import com.zeta_servlet.model.Alternativa;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/AlterarAtividade")
public class AlterarAtividade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            AtividadeDAO atividadeDao = new AtividadeDAO();
            AlternativaDAO alternativaDao = new AlternativaDAO();
            PerguntaDAO perguntaDao = new PerguntaDAO();

            int id = Integer.parseInt(request.getParameter("id"));
            double pontuacao = Double.parseDouble(request.getParameter("pontos"));
            String textoPergunta = request.getParameter("pergunta");
            int alternativaSelecionada = Integer.parseInt(request.getParameter("alternativaSelecionada"));
            String novaAlternativa = request.getParameter("novaAlternativa");
            boolean correta = Boolean.parseBoolean(request.getParameter("correta"));
            int idAula = Integer.parseInt(request.getParameter("idAula"));

            System.out.println("=== INICIANDO ALTERAÇÃO (REFATORADO) ===");
            System.out.println("ID Atividade: " + id);
            System.out.println("Alternativa selecionada: " + alternativaSelecionada);
            System.out.println("Nova alternativa: " + novaAlternativa);
            System.out.println("É correta: " + correta);

            // Buscar a atividade existente
            Atividade atividadeExistente = atividadeDao.buscarPorId(id).get(0);

            // 1. Atualizar pontuação
            int resultadoPontuacao = atividadeDao.updatePontuacao(atividadeExistente, pontuacao);
            System.out.println("Pontuação atualizada: " + resultadoPontuacao);

            // 2. Atualizar pergunta - buscar do banco
            List<Pergunta> todasPerguntas = perguntaDao.buscar();
            Pergunta perguntaParaAtualizar = null;

            for (Pergunta p : todasPerguntas) {
                if (p.getIdAtividade() == id) {
                    perguntaParaAtualizar = p;
                    System.out.println("Pergunta encontrada - ID: " + p.getId() +
                            ", Texto atual: '" + p.getPergunta() + "'" +
                            ", Novo texto: '" + textoPergunta + "'");
                    break;
                }
            }

            if (perguntaParaAtualizar != null && !perguntaParaAtualizar.getPergunta().equals(textoPergunta)) {
                int resultadoPergunta = perguntaDao.updatePergunta(perguntaParaAtualizar, textoPergunta);
                System.out.println("Pergunta atualizada: " + resultadoPergunta);
            } else {
                System.out.println("Pergunta não alterada (texto igual ou não encontrada)");
            }

            // 3. Buscar alternativas usando o DAO
            List<Alternativa> alternativasReais = alternativaDao.buscarPorIdAtividade(id);
            System.out.println("Alternativas encontradas: " + alternativasReais.size());

            for (int i = 0; i < alternativasReais.size(); i++) {
                Alternativa alt = alternativasReais.get(i);
                System.out.println("Alt " + i + " - ID: " + alt.getId() + " | Texto: " + alt.getAlternativa() + " | Correta: " + alt.isCorreto());
            }

            // 4. Atualizar a alternativa
            if (alternativaSelecionada >= 0 && alternativaSelecionada < alternativasReais.size()) {
                Alternativa alternativaReal = alternativasReais.get(alternativaSelecionada);
                System.out.println("Alternativa selecionada - ID: " + alternativaReal.getId() + ", Texto: " + alternativaReal.getAlternativa());

                // Atualizar texto
                int resultadoTexto = alternativaDao.updateAlternativa(alternativaReal, novaAlternativa);
                System.out.println("Texto atualizado: " + resultadoTexto);

                // Gerenciar campo "correta"
                if (correta) {
                    // Marcar todas como falsas
                    int resultadoFalsas = alternativaDao.marcarTodasComoFalsas(id);
                    System.out.println("Alternativas marcadas como falsas: " + resultadoFalsas);

                    // Marcar esta como correta
                    int resultadoCorreta = alternativaDao.marcarComoCorreta(alternativaReal.getId());
                    System.out.println("Alternativa marcada como correta: " + resultadoCorreta);
                }

            } else {
                System.out.println("Índice de alternativa inválido: " + alternativaSelecionada);
            }

            System.out.println("ALTERAÇÃO CONCLUÍDA");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/menuAtividade");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println("Erro durante a alteração: " + e.getMessage());
            e.printStackTrace();

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/erroAlterarAdm.jsp");
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            dispatcher.forward(request, response);
        }
    }
}