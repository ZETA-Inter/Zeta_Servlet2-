package com.zeta_servlet.controller.home;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/logout")
public class Logout extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                HttpSession session = request.getSession();
                session.setAttribute("email", null);
                session.setAttribute("senha", null);
                session.setAttribute("role", null);
                request.getRequestDispatcher("/admin").forward(request, response);

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1+"IF");
            request.getRequestDispatcher("/admin").forward(request, response);

        }
    }
}
