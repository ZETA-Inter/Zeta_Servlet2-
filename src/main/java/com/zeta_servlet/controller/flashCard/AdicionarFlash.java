package com.zeta_servlet.controller.flashCard;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.FlashCardDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.FlashCard;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/adicionarFlashCompleto")
public class AdicionarFlash extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FlashCardDAO flashCardDAO = new FlashCardDAO();
            String frente = request.getParameter("frente");
            String verso = request.getParameter("verso");
            int idAula = Integer.parseInt(request.getParameter("idAula"));
            FlashCard flashCard = new FlashCard(0, frente, verso, idAula);
            flashCardDAO.inserir(flashCard);
            request.getRequestDispatcher("/menuFlash").forward(request, response);

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
