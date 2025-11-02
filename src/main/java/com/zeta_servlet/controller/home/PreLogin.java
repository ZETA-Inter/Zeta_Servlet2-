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
                response.sendRedirect("html/admin.html");
            }
            else{
                request.getRequestDispatcher("/home").forward(request, response);
            }
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
