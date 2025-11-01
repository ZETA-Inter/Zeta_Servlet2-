package com.zeta_servlet.controller.flashCard;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
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
import java.util.List;

@WebServlet(value = "/alterarTexto")
public class PrepAlterarTexto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TextoCorridoDAO textoCorridoDAO = new TextoCorridoDAO();
            List<TextoCorrido> liT;

            int i = Integer.parseInt(request.getParameter("i"));
            System.out.println(i);
            liT=TextoCorridoDAO.buscarPorId(i);
            TextoCorrido textoCorrido = liT.get(0);
            request.setAttribute("flash", textoCorrido);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarTexto.jsp").forward(request, response);

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);

        }
    }
}
