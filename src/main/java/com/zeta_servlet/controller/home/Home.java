package com.zeta_servlet.controller.home;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/home")
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
            System.out.println("redirecionando a home");
        } catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);

        }
    }
}