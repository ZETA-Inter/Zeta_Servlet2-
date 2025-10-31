package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.ProdutorDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AlterarProdutor", value = "/AlterarProdutor")
public class AlterarProdutor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProdutorDAO produtorDao = new ProdutorDAO();
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            double pontos = Double.parseDouble(request.getParameter("pontos"));
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            int aulasFeitas = Integer.parseInt(request.getParameter("aulas"));
            int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
            int idAssinatura = Integer.parseInt(request.getParameter("idAssinatura"));
            int id = Integer.parseInt(request.getParameter("id"));

            produtorDao.updateEmail(produtorDao.buscarPorID(id).get(0), email);
            produtorDao.alterarPrimeiroNome(produtorDao.buscarPorID(id).get(0), nome);
            produtorDao.alterarUltimoNome(sobrenome, id);
            produtorDao.updateSenha(produtorDao.buscarPorID(id).get(0), senha);
            System.out.println(1 + "alterarCompleto");
            request.getRequestDispatcher("menuProdutor").forward(request, response);


        }catch (Exception e){
            request.getRequestDispatcher("WEB-INF/jsp/erroAlterarAdm.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1+"IF");

        }


    }
}
