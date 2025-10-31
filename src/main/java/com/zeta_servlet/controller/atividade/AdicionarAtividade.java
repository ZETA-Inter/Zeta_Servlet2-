package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.*;
import com.zeta_servlet.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adicionarAtividade")
public class AdicionarAtividade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Dados da Atividade
            double pontuacao = Double.parseDouble(request.getParameter("pontos"));
            String perguntaTexto = request.getParameter("pergunta");
            int idAula = Integer.parseInt(request.getParameter("idAula"));

            // Alternativas recebidas
            String alt1 = request.getParameter("alternativa1");
            String alt2 = request.getParameter("alternativa2");
            String alt3 = request.getParameter("alternativa3");
            String alt4 = request.getParameter("alternativa4");

            int correta = Integer.parseInt(request.getParameter("correta"));

            // Montando listas
            List<Pergunta> perguntas = new ArrayList<>();
            perguntas.add(new Pergunta(perguntaTexto, 0)); // id_atividade ainda desconhecido

            List<Alternativa> alternativas = new ArrayList<>();
            alternativas.add(new Alternativa(alt1, 0, correta == 1));
            alternativas.add(new Alternativa(alt2, 0, correta == 2));
            alternativas.add(new Alternativa(alt3, 0, correta == 3));
            alternativas.add(new Alternativa(alt4, 0, correta == 4));

            // Cria objeto Atividade
            Atividade atividade = new Atividade(pontuacao, idAula, perguntas, alternativas);

            // Salva no banco via DAO
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            atividadeDAO.inserir(atividade);

            System.out.println("Atividade adicionada");

            request.getRequestDispatcher("menuAtividade").forward(request, response);


            }catch (Exception e){
                request.getRequestDispatcher("html/erroCriarAtividade.html").forward(request, response);
                ExceptionHandler eh = new ExceptionHandler(e);
                eh.printExeption();
                request.setAttribute("option", -1);
                System.out.println(-1);

            }
        }
    }
