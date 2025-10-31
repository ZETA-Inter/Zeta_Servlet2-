package com.zeta_servlet.controller.flashCard;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.AdmDAO;
import com.zeta_servlet.daos.FlashCardDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.FlashCard;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/alterarFlash")
public class PrepAlterarFlash extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FlashCardDAO flashCardDAO = new FlashCardDAO();
            List<FlashCard> liF;

            int i = Integer.parseInt(request.getParameter("i"));
            System.out.println(i);
            liF=flashCardDAO.buscarPorId(i);
            FlashCard flash = liF.get(0);
            request.setAttribute("flash", flash);
            System.out.println(1+"alterar");
            request.getRequestDispatcher("WEB-INF/jsp/alterarFlash.jsp").forward(request, response);

        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            System.out.println(-1);

        }
    }
}
