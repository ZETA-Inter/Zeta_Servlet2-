package com.zeta_servlet.controller.assinatura;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;

import com.zeta_servlet.daos.AssinaturaDAO;
import com.zeta_servlet.model.Assinatura;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/menuAss")
public class MenuAssinatura extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
            List<Assinatura> liAs;
            liAs=assinaturaDAO.buscar();
            request.setAttribute("list", liAs);
            System.out.println(1+"menu");
            request.getRequestDispatcher("WEB-INF/jsp/menuAssinatura.jsp").forward(request, response);

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/jsp/menuAssinatura.jsp").forward(request, response);

        }
    }

}
