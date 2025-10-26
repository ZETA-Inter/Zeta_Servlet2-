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

@WebServlet(value = "/alterarAdmCompleto")
public class AlterarAdm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdmDAO admDAO = new AdmDAO();
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            int id = Integer.parseInt(request.getParameter("id"));
            Adm adm = new Adm(email, id, senha);
            admDAO.updateEmail(adm, email);
            admDAO.updateSenha(adm, senha);
            System.out.println(1+"alterarCompleto");
            request.getRequestDispatcher("/menuAdm").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("/menuAdm").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }

}
