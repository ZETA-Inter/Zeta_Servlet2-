package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AtividadeDAO;
import com.zeta_servlet.model.Atividade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/PrepAlterarAtividade")
public class PrepAlterarAtividade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            List<Atividade> liAti;
            liAti = atividadeDAO.buscar();
            int i = Integer.parseInt(request.getParameter("i"));
            System.out.println(i);
            Atividade ativ = liAti.get(i);
            request.setAttribute("ativ", ativ);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarAtividade.jsp").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/menuAtividade.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }

    }
}
