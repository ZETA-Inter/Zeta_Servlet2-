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
            System.out.println(i);
            Aula au = liAu.get(i);
            request.setAttribute("au", au);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarAula.jsp").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/menuAula.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }
}
