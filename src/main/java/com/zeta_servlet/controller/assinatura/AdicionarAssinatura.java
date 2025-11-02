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
import java.math.BigDecimal;

@WebServlet(value = "/adicionarAss")
public class AdicionarAssinatura extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
            String tpPlano = request.getParameter("tpPlano");
            int qtdCurso = Integer.parseInt(request.getParameter("qtdCurso"));
            String descricao = request.getParameter("descricao");
            BigDecimal precoFixo = BigDecimal.valueOf(Double.valueOf(request.getParameter("precoFixo")));
            BigDecimal precoProdutor = BigDecimal.valueOf(Double.valueOf(request.getParameter("precoProdutor")));

            Assinatura assinatura = new Assinatura(0, tpPlano, qtdCurso, descricao, precoFixo, precoProdutor);
            assinaturaDAO.inserir(assinatura);
            request.getRequestDispatcher("/menuAss").forward(request, response);

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
