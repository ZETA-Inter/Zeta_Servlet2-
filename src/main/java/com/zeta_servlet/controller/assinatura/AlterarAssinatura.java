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
import java.math.BigDecimal;

@WebServlet(value = "/alterarAssCompleto")
public class AlterarAssinatura extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
            String tpPlano = request.getParameter("tpPlano");
            int qtdCurso = Integer.parseInt(request.getParameter("qtdCurso"));
            String descricao = request.getParameter("descricao");
            BigDecimal precoFixo = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precoProdutor")));
            BigDecimal precoProdutor = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precoProdutor")));
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            System.out.println("passou");
            Assinatura assinatura = new Assinatura(id, tpPlano, qtdCurso, descricao, precoFixo, precoProdutor);
            assinaturaDAO.updateTp_Plano(assinatura, tpPlano);
            assinaturaDAO.updatebenefDescPlno(assinatura, descricao);
            assinaturaDAO.updateBnfQtdCurso(assinatura, qtdCurso);
            assinaturaDAO.updatePreco_fixo(assinatura, precoFixo);
            assinaturaDAO.updatePrecoQtdProdutores(assinatura, precoProdutor);
            System.out.println(1 + "alterarCompleto");
            request.getRequestDispatcher("/menuAss").forward(request, response);


        }catch (Exception e){
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/jsp/erroAlterarAssinatura.jsp").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1+"IF");

        }
    }

}
