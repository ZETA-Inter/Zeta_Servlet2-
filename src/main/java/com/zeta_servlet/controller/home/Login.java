package com.zeta_servlet.controller.home;

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
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("senha", email);
                session.setAttribute("role", "admin");
                request.getRequestDispatcher("/home").forward(request, response);


            }
            else {

                response.sendRedirect("WEB-INF/jsp/resultadoLogin.jsp");
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

    private int autenticar(String email, String senha){
        try {
            AdmDAO admDAO = new AdmDAO();
            List<Adm> adms = admDAO.buscarPorEmail(email);
            if (!adms.isEmpty()){
                if (adms.get(0).getEmail().equals(email) && adms.get(0).getSenha().equals(senha)) {
                    return 1;
                }
                else {
                    return 0;
                }

            }else{
                return 0;
            }

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            return -1;
        }
    }
}
