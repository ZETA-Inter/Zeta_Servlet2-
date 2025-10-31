package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.AlternativaDAO;
import com.zeta_servlet.daos.AtividadeDAO;
import com.zeta_servlet.daos.PerguntaDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.Alternativa;
import com.zeta_servlet.model.Atividade;
import com.zeta_servlet.model.Pergunta;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/AlterarAtividade")
public class AlterarAtividade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            AtividadeDAO atividadeDao = new AtividadeDAO();
            AlternativaDAO alternativaDao = new AlternativaDAO();
            PerguntaDAO perguntaDao = new PerguntaDAO();
            double pontuacao = Double.parseDouble(request.getParameter("pontos"));
            String pergunta = request.getParameter("pergunta");
            String alternativa = request.getParameter("alternativa");
            String correto = request.getParameter("correta");

            Boolean correta = true;
            if ((correto.equals("Sim") || correto.equals("sim"))) {
                correta = true;
            }
            else if ((correto.equals("Não") || correto.equals("não"))) {
                correta = false;
            }

            int idAula = Integer.parseInt(request.getParameter("idAula"));
            int id = Integer.parseInt(request.getParameter("id"));

            System.out.println( id + " passou");

            atividadeDao.updatePontuacao(atividadeDao.buscarPorId(id).get(0), pontuacao);
            perguntaDao.updateAlternativa(perguntaDao.buscarPorIdAula(idAula).get(0), pergunta);
            alternativaDao.updateAlternativa(alternativaDao.buscarPorIdAula(id).get(0), alternativa);
            alternativaDao.updateCorreto(alternativaDao.buscarPorIdAula(id).get(0), correta);

            System.out.println(1 + "alterarCompleto");
            request.getRequestDispatcher("/menuAtividade").forward(request, response);


        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/erroAlterarAdm.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1+"IF");

        }

    }
}
