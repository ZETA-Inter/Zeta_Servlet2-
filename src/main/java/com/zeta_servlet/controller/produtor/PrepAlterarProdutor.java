package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.ProdutorDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.Produtor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrepAlterarProdutor", value = "/PrepAlterarProdutor")
public class PrepAlterarProdutor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProdutorDAO produtorDAO = new ProdutorDAO();
            List<Produtor> liProdu;
            liProdu = produtorDAO.buscar();
            int i = Integer.parseInt(request.getParameter("i"));
            System.out.println(i);
            Produtor produtor = liProdu.get(i);
            request.setAttribute("produ", produtor);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarProdutor.jsp").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/menuProdutor.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }

    }
}
