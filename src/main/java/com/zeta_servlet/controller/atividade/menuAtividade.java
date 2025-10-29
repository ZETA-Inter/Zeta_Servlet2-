package com.zeta_servlet.controller.atividade;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.*;
import com.zeta_servlet.daos.AtividadeDAO;
import com.zeta_servlet.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/menuAtividade")
public class menuAtividade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            List<Atividade> liAti;
            liAti=atividadeDAO.buscar();
            request.setAttribute("list", liAti);
            System.out.println(1+"menu");
            request.getRequestDispatcher("WEB-INF/jsp/menuAtividade.jsp").forward(request, response);
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/jsp/menuAtividade.jsp").forward(request, response);

        }
    }
}
