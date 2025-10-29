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

import java.io.IOException;

@WebServlet(value = "/adicionarAdm")
public class AdicionarAdm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdmDAO admDAO = new AdmDAO();
            Regex regex = new Regex();
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            if (regex.validarEmail(email) && regex.validarSenha(senha)) {
                Adm adm = new Adm(email, 0, senha);
                admDAO.inserir(adm);
                System.out.println(1 + "criarAdm");
                request.getRequestDispatcher("/menuAdm").forward(request, response);
            }
            else{
                System.out.println("Invalido");
                request.getRequestDispatcher("html/InvalidoAdm.html").forward(request, response);
            }
        }catch (Exception e){
            request.getRequestDispatcher("html/erroCriarAdm.html").forward(request, response);
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);

        }
    }

}
