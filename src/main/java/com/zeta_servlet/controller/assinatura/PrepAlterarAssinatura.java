package com.zeta_servlet.controller.assinatura;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AssinaturaDAO;
import com.zeta_servlet.model.Assinatura;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/alterarAss")
public class PrepAlterarAssinatura extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
            List<Assinatura> liAs;
            liAs=assinaturaDAO.buscar();
            int i = Integer.parseInt(request.getParameter("i"));
            System.out.println(i);
            Assinatura ass = liAs.get(i);
            request.setAttribute("ass", ass);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarAssinatura.jsp").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/menuAssinatura.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }
}
