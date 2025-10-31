package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AtividadeDAO;
import com.zeta_servlet.daos.ProdutorDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeletarProdutor", value = "/DeletarProdutor")
public class DeletarProdutor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProdutorDAO produtorDAO = new ProdutorDAO();
            int i = Integer.parseInt(request.getParameter("id"));
            System.out.println(i + " - Id da atividade deletada");
            produtorDAO.remover(i);
            request.getRequestDispatcher("WEB-INF/jsp/menuProdutor.jsp");

        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/menuProdutor.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
        }

    }
}
