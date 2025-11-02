package com.zeta_servlet.controller.aula;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AulaDAO;
import com.zeta_servlet.model.Aula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/alterarAula")
public class PrepAlterarAula extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AulaDAO aulaDAO = new AulaDAO();
            List<Aula> liAu;
            liAu=aulaDAO.buscar();
            int i = Integer.parseInt(request.getParameter("i"));
            Aula au = liAu.get(i);
            request.setAttribute("au", au);
            request.getRequestDispatcher("WEB-INF/jsp/alterarAula.jsp").forward(request, response);

        }catch (Exception e){
            request.setAttribute("mensagem", e.getMessage());
            request.setAttribute("erro", e.getClass().getSimpleName());
            request.getRequestDispatcher("WEB-INF/errorPage/erroJava.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace();

        }
    }
}
