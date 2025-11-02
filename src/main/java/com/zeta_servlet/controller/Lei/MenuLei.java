package com.zeta_servlet.controller.Lei;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.LeiDAO;
import com.zeta_servlet.model.Lei;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/menuLei")
public class MenuLei extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            LeiDAO leiDAO = new LeiDAO();
            List<Lei> liL;
            int id;
            String nomeAula = request.getParameter("nomeAula");
            if (nomeAula==null){
                request.setAttribute("nomeAula", session.getAttribute("nomeAula"));
                id = (int) session.getAttribute("idAula");
            }
            else {
                request.setAttribute("nomeAula", nomeAula);
                session.setAttribute("nomeAula", nomeAula);
                id = Integer.parseInt(request.getParameter("idAula"));
                session.setAttribute("idAula", id);
            }
            liL = leiDAO.buscarPorIdAula(id);
            request.setAttribute("list", liL);
            request.getRequestDispatcher("WEB-INF/jsp/menuLei.jsp").forward(request, response);


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
