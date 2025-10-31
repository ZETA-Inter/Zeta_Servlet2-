package com.zeta_servlet.controller.aula;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AulaDAO;
import com.zeta_servlet.daos.FlashCardDAO;
import com.zeta_servlet.daos.LeiDAO;
import com.zeta_servlet.daos.TextoCorridoDAO;
import com.zeta_servlet.model.Aula;
import com.zeta_servlet.model.FlashCard;
import com.zeta_servlet.model.Lei;
import com.zeta_servlet.model.TextoCorrido;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/adicionarAula")
public class AdicionarAula extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AulaDAO aulaDAO = new AulaDAO();
            TextoCorridoDAO textoCorridoDAO = new TextoCorridoDAO();
            FlashCardDAO flashCardDAO = new FlashCardDAO();
            LeiDAO leiDAO = new LeiDAO();

            String nome = request.getParameter("nome");
            String textoCorrido = request.getParameter("textoCorrido");
            String leiT = request.getParameter("lei");
            String frente = request.getParameter("frente");
            String verso = request.getParameter("verso");
            int idModulo = Integer.parseInt(request.getParameter("idModulo"));

                Aula aula = new Aula(0, nome, idModulo);
                aulaDAO.inserir(aula);
                int id = aulaDAO.buscarUltimoId();
                // criando outras sub-tabelas

                //Criando objetos
                FlashCard flashCard = new FlashCard(0, frente, verso, id);
                TextoCorrido textoCorrido1 = new TextoCorrido(0, textoCorrido, id);
                Lei lei = new Lei(0, leiT, id);
            System.out.println("passou1");
                // inserindo nas tabelas
                flashCardDAO.inserir(flashCard);
                textoCorridoDAO.inserir(textoCorrido1);
                leiDAO.inserir(lei);
            System.out.println("passou2");


                request.getRequestDispatcher("/menuAula").forward(request, response);
        }catch (Exception e){
            request.getRequestDispatcher("html/erroCriarAula.html").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }

}
