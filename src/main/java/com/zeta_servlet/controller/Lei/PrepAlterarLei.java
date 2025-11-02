package com.zeta_servlet.controller.Lei;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.LeiDAO;
import com.zeta_servlet.model.Lei;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/alterarLei")
public class PrepAlterarLei extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LeiDAO leiDAO = new LeiDAO();
            List<Lei> liL;

            int i = Integer.parseInt(request.getParameter("i"));
            liL=leiDAO.buscarPorId(i);
            Lei lei = liL.get(0);
            request.setAttribute("lei", lei);
            request.getRequestDispatcher("WEB-INF/jsp/alterarLei.jsp").forward(request, response);

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
