package com.zeta_servlet.controller.assinatura;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;

import com.zeta_servlet.daos.AssinaturaDAO;
import com.zeta_servlet.model.Assinatura;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/adicionarAss")
public class AdicionarAssinatura extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            Assinatura assinatura = new Assinatura();
            assinaturaDAO.inserir(assinatura);
            System.out.println(1 + "criarAssinatura");
            request.getRequestDispatcher("/menuAss").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("html/erroCriarAssinatura.html").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }

}
