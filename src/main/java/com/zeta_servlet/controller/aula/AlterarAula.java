package com.zeta_servlet.controller.aula;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AulaDAO;
import com.zeta_servlet.model.Aula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/alterarAulaCompleto")
public class AlterarAula extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AulaDAO aulaDAO = new AulaDAO();
            String nome = request.getParameter("nome");
            int idModulo = Integer.parseInt(request.getParameter("idModulo"));
            int id = Integer.parseInt(request.getParameter("id"));
            Aula aula = new Aula(id, nome, idModulo);
            aulaDAO.updateModulo(aula, idModulo);
            aulaDAO.updateNome(aula, nome);
            request.getRequestDispatcher("/menuAula").forward(request, response);


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
