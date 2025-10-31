package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AssinaturaDAO;
import com.zeta_servlet.daos.ProdutorDAO;
import com.zeta_servlet.model.Assinatura;
import com.zeta_servlet.model.Produtor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/menuProdutor")
public class MenuProdutor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProdutorDAO produtorDAO = new ProdutorDAO();
            List<Produtor> liProd;
            liProd = produtorDAO.buscar();
            request.setAttribute("list", liProd);
            System.out.println(1+"menu");
            request.getRequestDispatcher("WEB-INF/jsp/menuProdutor.jsp").forward(request, response);

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/jsp/menuProdutor.jsp").forward(request, response);

        }

    }
}
