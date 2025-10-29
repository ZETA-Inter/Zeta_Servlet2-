package com.zeta_servlet.controller.assinatura;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.AssinaturaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/deletarAss")
public class DeletarAssinatura extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
            int i = Integer.parseInt(request.getParameter("id"));
            System.out.println(i);
            System.out.println(1+"deletar");
            assinaturaDAO.remover(i);
            request.getRequestDispatcher("/menuAss").forward(request, response);


        }catch (Exception e){
            request.getRequestDispatcher("/menuAss").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }

}
