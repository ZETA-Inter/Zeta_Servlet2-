package com.zeta_servlet.controller.home;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/home")
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("paginaAnterior", request.getRequestURI());
        try {
            request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensagem", e.getMessage());
            request.setAttribute("erro", e.getClass().getSimpleName());
            request.getRequestDispatcher("WEB-INF/errorPage/erroJava.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace();

        }
    }
}