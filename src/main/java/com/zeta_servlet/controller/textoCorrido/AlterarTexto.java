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

@WebServlet(value = "/alterarTextoCompleto")
public class AlterarTexto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TextoCorridoDAO textoCorridoDAO = new TextoCorridoDAO();
            String texto = request.getParameter("texto");
            int idAula = Integer.parseInt(request.getParameter("idAula"));
            int id = Integer.parseInt(request.getParameter("id"));
            TextoCorrido textoCorrido = new TextoCorrido(id, texto, idAula);
            textoCorridoDAO.updateIdAula(textoCorrido);
            textoCorridoDAO.updateTextoCorrido(textoCorrido);

            request.setAttribute("id", idAula);


            request.getRequestDispatcher("/menuTexto").forward(request, response);


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
