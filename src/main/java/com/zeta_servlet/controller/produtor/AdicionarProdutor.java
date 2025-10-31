package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.daos.ProdutorDAO;
import com.zeta_servlet.model.Produtor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/AdicionarProdutor")
public class AdicionarProdutor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String etapa = request.getParameter("etapa");

        if (etapa.equals("1")){

            String cpf = request.getParameter("cpf");
            LocalDate dtNascimento = LocalDate.parse(request.getParameter("dtNascimento"));
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            int pontos = Integer.parseInt(request.getParameter("pontos"));

            session.setAttribute("cpf", cpf);
            session.setAttribute("dtNascimento", dtNascimento);
            session.setAttribute("email", email);
            session.setAttribute("senha", senha);
            session.setAttribute("pontos", pontos);

            request.getRequestDispatcher("/html/adicionarProdutorContinuacao.html").forward(request, response);
        }

        if (etapa.equals("2")) {

            String cpf = (String) session.getAttribute("cpf");
            LocalDate dtNascimento = (LocalDate) session.getAttribute("dtNascimento");
            String email = (String) session.getAttribute("email");
            String senha = (String) session.getAttribute("senha");
            int pontos = (int) session.getAttribute("pontos");


            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            int aulasFeitas = Integer.parseInt(request.getParameter("aulas"));
            int idFornecedor = Integer.parseInt(request.getParameter("idForn"));
            int idAssinatura = Integer.parseInt(request.getParameter("idAss"));

            Produtor produtor = new Produtor(cpf, dtNascimento, email, senha, pontos, nome, sobrenome, aulasFeitas, idFornecedor, idAssinatura);

            ProdutorDAO produtorDAO = new ProdutorDAO();

            produtorDAO.inserir(produtor);

            session.removeAttribute("cpf");
            session.removeAttribute("dtNascimento");
            session.removeAttribute("email");
            session.removeAttribute("senha");
            session.removeAttribute("pontos");


            request.getRequestDispatcher("WEB-INF/jsp/menuProdutor.jsp").forward(request, response);

        }

    }
}
