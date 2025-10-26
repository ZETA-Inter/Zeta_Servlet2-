package com.zeta_servlet.controller;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.model.Adm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/loginAdmin")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String senha = request.getParameter("userpassword");
            String email = request.getParameter("email");
            int i = autenticar(email, senha);
            if (i>0) {
                request.setAttribute("option", i);
                System.out.println(i);
                HttpSession session = request.getSession();
                session.setAttribute("user", email);
                session.setAttribute("role", "admin");
                request.getRequestDispatcher("/home").forward(request, response);


            }
            else {
                request.setAttribute("option", i);
                System.out.println(i);
                request.getRequestDispatcher("WEB-INF/jsp/resultadoLogin.jsp").forward(request, response);
            }
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            request.setAttribute("option", -1);
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/jsp/resultadoLogin.jsp").forward(request, response);

        }
    }

    private int autenticar(String email, String senha){
        try {
            AdmDAO admDAO = new AdmDAO();
            List<Adm> adms = admDAO.buscarPorEmail(email);
            if (!adms.isEmpty()){
                if (adms.get(0).getEmail().equals(email) && adms.get(0).getSenha().equals(senha)) {
                    System.out.println(1);
                    return 1;
                }
                else {
                    System.out.println(0+"errado");
                    return 0;
                }

            }else{
                System.out.println(-1);
                return 0;
            }

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);
            return -1;
        }
    }
}
