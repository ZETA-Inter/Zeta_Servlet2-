package com.zeta_servlet.controller.aula;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AulaDAO;
import com.zeta_servlet.daos.Flash_cardDAO;
import com.zeta_servlet.daos.LeiDAO;
import com.zeta_servlet.daos.Texto_corridoDAO;
import com.zeta_servlet.model.Aula;
import com.zeta_servlet.model.Flash_card;
import com.zeta_servlet.model.Lei;
import com.zeta_servlet.model.Texto_corrido;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/adicionarAula")
public class AdicionarAula extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AulaDAO aulaDAO = new AulaDAO();
            Texto_corridoDAO textoCorridoDAO = new Texto_corridoDAO();
            Flash_cardDAO flashCardDAO = new Flash_cardDAO();
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
                Flash_card flashCard = new Flash_card(0, frente, verso, id);
                Texto_corrido texto_corrido = new Texto_corrido(0, textoCorrido, id);
                Lei lei = new Lei(0, leiT, id);
            System.out.println("passou1");
                // inserindo nas tabelas
                flashCardDAO.inserir(flashCard);
                textoCorridoDAO.inserir(texto_corrido);
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
