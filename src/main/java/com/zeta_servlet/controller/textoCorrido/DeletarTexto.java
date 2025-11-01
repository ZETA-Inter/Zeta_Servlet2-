package com.zeta_servlet.controller.textoCorrido;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.TextoCorridoDAO;
import com.zeta_servlet.model.TextoCorrido;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/deletarTexto")
public class DeletarTexto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TextoCorridoDAO textoCorridoDAO = new TextoCorridoDAO();
            int i = Integer.parseInt(request.getParameter("id"));
            System.out.println(i);
            System.out.println(1+"deletar");
            textoCorridoDAO.remover(i);
            request.getRequestDispatcher("/menuTexto").forward(request, response);


        }catch (Exception e){
            request.getRequestDispatcher("/menuTexto").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);

        }
    }

}
