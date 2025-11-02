package com.zeta_servlet.controller.Lei;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.FlashCardDAO;
import com.zeta_servlet.daos.LeiDAO;
import com.zeta_servlet.daos.TextoCorridoDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.FlashCard;
import com.zeta_servlet.model.Lei;
import com.zeta_servlet.model.TextoCorrido;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/adicionarLeiCompleto")
public class AdicionarLei extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LeiDAO leiDAO = new LeiDAO();
            String lei1 = request.getParameter("lei");
            int idAula = Integer.parseInt(request.getParameter("idAula"));
            Lei lei = new Lei(0, lei1, idAula);
            leiDAO.inserir(lei);
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
