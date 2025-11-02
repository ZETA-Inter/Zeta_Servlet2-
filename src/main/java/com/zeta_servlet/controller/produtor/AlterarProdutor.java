package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.ProdutorDAO;
import com.zeta_servlet.model.Produtor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/AlterarProdutor")
public class AlterarProdutor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AlterarProdutor iniciado\n");

        try {
            ProdutorDAO produtorDao = new ProdutorDAO();

            // Debug de todos os parâmetros recebidos
            System.out.println("Parâmetros Recebidos:");
            System.out.println("CPF: " + request.getParameter("cpf"));
            System.out.println("dtNascimento: " + request.getParameter("dtNascimento"));
            System.out.println("Email: " + request.getParameter("email"));
            System.out.println("Senha: " + request.getParameter("senha"));
            System.out.println("Pontos: " + request.getParameter("pontos"));
            System.out.println("Nome: " + request.getParameter("nome"));
            System.out.println("Sobrenome: " + request.getParameter("sobrenome"));
            System.out.println("Aulas: " + request.getParameter("aulas"));
            System.out.println("ID Fornecedor: " + request.getParameter("idFornecedor"));
            System.out.println("ID Assinatura: " + request.getParameter("idAssinatura"));

            // Coleta dos parâmetros
            int id = Integer.parseInt(request.getParameter("id"));
            String cpf = request.getParameter("cpf");
            LocalDate dtNascimento = LocalDate.parse(request.getParameter("dtNascimento"));
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            int pontos = Integer.parseInt(request.getParameter("pontos"));
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            int aulasFeitas = Integer.parseInt(request.getParameter("aulas"));
            int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
            int idAssinatura = Integer.parseInt(request.getParameter("idAssinatura"));

            Produtor produtor = new Produtor(id, cpf, dtNascimento, email, senha, pontos, nome, sobrenome, aulasFeitas, idFornecedor, idAssinatura);

            // Executar update com verificação
            int resultado;

            resultado = produtorDao.updateGeral(produtor);
            System.out.println("Update Geral: " + (resultado > 0 ? "Sucesso" : "Falha") + " (" + resultado + " linhas)");

            System.out.println("Alterar produtor finalizado");

            request.getRequestDispatcher("/menuProdutor").forward(request, response);

        } catch (Exception e) {
            System.err.println(" Erro em AlterarProdutor");
            System.err.println("Mensagem: " + e.getMessage());
            e.printStackTrace();

            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();

            request.setAttribute("erro", "Erro ao alterar produtor: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/erroAlterarProdutor.jsp").forward(request, response);
        }
    }
}