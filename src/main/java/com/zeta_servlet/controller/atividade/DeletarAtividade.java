package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AtividadeDAO;
import com.zeta_servlet.model.Atividade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/DeletarAtividade")
public class DeletarAtividade extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            int indice = Integer.parseInt(request.getParameter("i"));
            System.out.println("Índice recebido: " + indice);

            List<Atividade> atividades = atividadeDAO.buscar();

            if (indice >= 0 && indice < atividades.size()) {
                Atividade atividade = atividades.get(indice);
                int idAtividade = atividade.getId();

                System.out.println("DELETANDO Atividade ID: " + idAtividade);

                // ✅ Usar o método corrigido
                boolean resultado = atividadeDAO.remover(idAtividade);

                System.out.println("Resultado deleção: " + resultado);

                if (resultado) {
                    System.out.println("ATIVIDADE " + idAtividade + " DELETADA COM SUCESSO");
                } else {
                    System.out.println("FALHA AO DELETAR ATIVIDADE " + idAtividade);
                }

            } else {
                System.out.println("Índice inválido: " + indice);
            }

            request.getRequestDispatcher("/menuAtividade").forward(request, response);

        } catch (Exception e) {
            System.out.println("Erro durante deleção: " + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/menuAtividade").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
        }
    }
}