package com.zeta_servlet.controller;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/alterarAdm")
public class PrepAlterarAdm extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdmDAO admDAO = new AdmDAO();
            List<Adm> liA;
            liA=admDAO.buscar();
            int i = Integer.parseInt(request.getParameter("i"));
            System.out.println(i);
            Adm adm = liA.get(i);
            request.setAttribute("adm", adm);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarAdministrador.jsp").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/menuAdministrador.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }
}
