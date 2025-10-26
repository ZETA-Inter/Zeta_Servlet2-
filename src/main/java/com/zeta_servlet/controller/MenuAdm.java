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

@WebServlet(value = "/menuAdm")
public class MenuAdm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdmDAO admDAO = new AdmDAO();
            List<Adm> liA;
            liA=admDAO.buscar();
            request.setAttribute("list", liA);
            System.out.println(1+"menu");
            request.getRequestDispatcher("WEB-INF/ADMINISTRADOR/menuAdministrador.jsp").forward(request, response);
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/ADMINISTRADOR/menuAdministrador.jsp").forward(request, response);

        }
    }

}
