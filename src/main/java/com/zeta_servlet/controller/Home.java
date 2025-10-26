package com.zeta_servlet.controller;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

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