package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AtividadeDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/DeletarAtividade")
public class DeletarAtividade extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            int i = Integer.parseInt(request.getParameter("id"));
            System.out.println(i + " - Id da atividade deletada");
            atividadeDAO.remover(i);
            response.sendRedirect(request.getContextPath() + "/menuAtividade");

        }catch (Exception e){
            request.getRequestDispatcher("/menuAtividade").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
        }

    }
}
