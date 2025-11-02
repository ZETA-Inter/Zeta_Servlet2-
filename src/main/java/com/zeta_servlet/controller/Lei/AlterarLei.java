package com.zeta_servlet.controller.lei;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.LeiDAO;
import com.zeta_servlet.daos.TextoCorridoDAO;
import com.zeta_servlet.model.Lei;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/alterarLeiCompleto")
public class AlterarLei extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LeiDAO leiDAO = new LeiDAO();
            String texto = request.getParameter("texto");
            int idAula = Integer.parseInt(request.getParameter("idAula"));
            int id = Integer.parseInt(request.getParameter("id"));
            Lei lei = new Lei(id, texto, idAula);
            leiDAO.updateLei(lei);
            leiDAO.updateIdAula(lei);
            request.setAttribute("id", idAula);


            request.getRequestDispatcher("/menuLei").forward(request, response);


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
