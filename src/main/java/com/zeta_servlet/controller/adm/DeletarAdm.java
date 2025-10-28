package com.zeta_servlet.controller.adm;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/deletarAdm")
public class DeletarAdm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdmDAO admDAO = new AdmDAO();
            int i = Integer.parseInt(request.getParameter("id"));
            System.out.println(i);
            System.out.println(1+"deletar");
            admDAO.remover(i);
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
