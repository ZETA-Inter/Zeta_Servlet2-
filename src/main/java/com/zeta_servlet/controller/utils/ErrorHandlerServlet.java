package com.zeta_servlet.controller.utils;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String errorCode = request.getParameter("code");
            String errorMessage = request.getParameter("message");

            request.setAttribute("errorCode", errorCode);
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("contextPath", request.getContextPath());

            request.getRequestDispatcher("/WEB-INF/errorPage/errorPage.jsp").forward(request, response);

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);
        }
    }
}
