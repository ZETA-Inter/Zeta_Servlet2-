package com.zeta_servlet.controller.produtor;

import com.zeta_servlet.daos.ProdutorDAO;
import com.zeta_servlet.model.Produtor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/AdicionarProdutor")
public class AdicionarProdutor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String etapa = request.getParameter("etapa");

        System.out.println("Etapa: " + etapa);

        if ("1".equals(etapa)) {
            processarEtapa1(request, response, session);
        } else if ("2".equals(etapa)) {
            processarEtapa2(request, response, session);
        }
    }

    private void processarEtapa1(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        try {
            // Coleta dos parâmetros
            String cpf = request.getParameter("cpf");
            String dtNascimentoStr = request.getParameter("dtNascimento");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String pontosStr = request.getParameter("pontos");

            System.out.println("Dados etapa 1:");
            System.out.println("CPF: " + cpf);
            System.out.println("Data: " + dtNascimentoStr);
            System.out.println("Email: " + email);
            System.out.println("Senha: " + senha);
            System.out.println("Pontos: " + pontosStr);

            // Conversões
            LocalDate dtNascimento = LocalDate.parse(dtNascimentoStr);
            int pontos = Integer.parseInt(pontosStr);

            // Salva na sessão
            session.setAttribute("cpf", cpf);
            session.setAttribute("dtNascimento", dtNascimento);
            session.setAttribute("email", email);
            session.setAttribute("senha", senha);
            session.setAttribute("pontos", pontos);

            System.out.println("Dados salvos na sessão - redirecionando para etapa 2");

            request.getRequestDispatcher("/html/adicionarProdutorContinuacao.html").forward(request, response);

        } catch (Exception e) {
            System.err.println("ERRO na etapa 1: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("erro", "Erro: " + e.getMessage());
            request.getRequestDispatcher("/html/adicionarProdutor.html").forward(request, response);
        }
    }

    private void processarEtapa2(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        try {

            // Verifica dados da sessão
            String cpf = (String) session.getAttribute("cpf");
            LocalDate dtNascimento = (LocalDate) session.getAttribute("dtNascimento");
            String email = (String) session.getAttribute("email");
            String senha = (String) session.getAttribute("senha");
            Integer pontos = (Integer) session.getAttribute("pontos");

            System.out.println("Dados da sessão:");
            System.out.println("CPF: " + cpf);
            System.out.println("Data: " + dtNascimento);
            System.out.println("Email: " + email);
            System.out.println("Senha: " + senha);
            System.out.println("Pontos: " + pontos);

            if (cpf == null) {
                throw new Exception("Dados da etapa 1 não encontrados na sessão");
            }

            // Dados da etapa 2
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String aulaStr = request.getParameter("aula");
            String idFornStr = request.getParameter("idForn");
            String idAssStr = request.getParameter("idAss");

            System.out.println("Dados etapa 2:");
            System.out.println("Nome: " + nome);
            System.out.println("Sobrenome: " + sobrenome);
            System.out.println("Aula: " + aulaStr);
            System.out.println("ID Fornecedor: " + idFornStr);
            System.out.println("ID Assinatura: " + idAssStr);

            // Conversões com valores padrão
            int aulasFeitas = (aulaStr == null || aulaStr.isEmpty()) ? 0 : Integer.parseInt(aulaStr);
            int idFornecedor = (idFornStr == null || idFornStr.isEmpty()) ? 0 : Integer.parseInt(idFornStr);
            int idAssinatura = (idAssStr == null || idAssStr.isEmpty()) ? 0 : Integer.parseInt(idAssStr);

            // Criar objeto Produtor
            System.out.println("Criando objeto Produtor...");
            Produtor produtor = new Produtor(cpf, dtNascimento, email, senha, pontos,
                    nome, sobrenome, aulasFeitas, idFornecedor, idAssinatura);

            System.out.println("Produtor criado: " + produtor.toString());

            // Inserir no banco
            System.out.println("Iniciando inserção no banco...");
            ProdutorDAO produtorDAO = new ProdutorDAO();
            int sucesso = produtorDAO.inserir(produtor);

            System.out.println("Resultado da inserção: " + sucesso);

            if (sucesso == 1) {
                System.out.println("Produtor inserido com SUCESSO!");
            } else {
                System.out.println("FALHA na inserção do produtor!");
            }

            // Limpar sessão
            session.removeAttribute("cpf");
            session.removeAttribute("dtNascimento");
            session.removeAttribute("email");
            session.removeAttribute("senha");
            session.removeAttribute("pontos");

            // Redirecionar
            request.getRequestDispatcher("/menuProdutor").forward(request, response);

        } catch (Exception e) {
            System.err.println("ERRO na etapa 2: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("erro", "Erro no cadastro: " + e.getMessage());
            request.getRequestDispatcher("/html/adicionarProdutor.html").forward(request, response);
        }
    }

    // Métodos auxiliares de validação
    private String validarCampoObrigatorio(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nomeCampo + " é obrigatório");
        }
        return valor.trim();
    }

    private LocalDate validarData(String data) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória");
        }
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }
    }

    private int validarInteiro(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nomeCampo + " é obrigatório");
        }
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nomeCampo + " deve ser um número válido");
        }
    }

    private int validarInteiroOpcional(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean validarSessaoEtapa1(HttpSession session) {
        return session.getAttribute("cpf") != null &&
                session.getAttribute("dtNascimento") != null &&
                session.getAttribute("email") != null &&
                session.getAttribute("senha") != null &&
                session.getAttribute("pontos") != null;
    }

    private void limparSessao(HttpSession session) {
        session.removeAttribute("cpf");
        session.removeAttribute("dtNascimento");
        session.removeAttribute("email");
        session.removeAttribute("senha");
        session.removeAttribute("pontos");
    }
}