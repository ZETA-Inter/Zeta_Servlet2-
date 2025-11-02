package com.zeta_servlet.controller.adm;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/alterarAdmCompleto")
public class AlterarAdm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdmDAO admDAO = new AdmDAO();
            Regex regex = new Regex();
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            int id = Integer.parseInt(request.getParameter("id"));
            if (regex.validarEmail(email) && regex.validarSenha(senha)) {
                Adm adm = new Adm(email, id, senha);
                admDAO.updateSenha(adm, senha);
                admDAO.updateEmail(adm, email);
                request.getRequestDispatcher("/menuAdm").forward(request, response);
            }
            else{
                request.getRequestDispatcher("html/invalidoAlterarAdm.html").forward(request, response);
            }


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