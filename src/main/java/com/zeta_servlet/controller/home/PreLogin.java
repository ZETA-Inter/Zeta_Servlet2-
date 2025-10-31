package com.zeta_servlet.controller.home;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/admin")
public class PreLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            String senha = (String) session.getAttribute("senha");

            if (email == null && senha == null) {
                request.getRequestDispatcher("/html/admin.html").forward(request, response);
            }
            else{
                request.getRequestDispatcher("/home").forward(request, response);
            }
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            request.getRequestDispatcher("WEB-INF/jsp/resultadoLogin.jsp").forward(request, response);

        }
    }
}
