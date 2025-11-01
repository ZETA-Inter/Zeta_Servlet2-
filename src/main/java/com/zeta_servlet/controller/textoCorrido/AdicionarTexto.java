package com.zeta_servlet.controller.textoCorrido;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.FlashCardDAO;
import com.zeta_servlet.daos.TextoCorridoDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.FlashCard;
import com.zeta_servlet.model.TextoCorrido;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/adicionarTextoCompleto")
public class AdicionarTexto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TextoCorridoDAO textoCorridoDAO = new TextoCorridoDAO();
            String texto = request.getParameter("texto");
            int idAula = Integer.parseInt(request.getParameter("idAula"));
            TextoCorrido textoCorrido = new TextoCorrido(0, texto, idAula);
            textoCorridoDAO.inserir(textoCorrido);
            System.out.println(1 + "criarFlash");
            request.getRequestDispatcher("/menuTexto").forward(request, response);
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);

        }
    }

}
