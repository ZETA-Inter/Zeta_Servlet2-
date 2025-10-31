package com.zeta_servlet.controller.flashCard;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.daos.FlashCardDAO;
import com.zeta_servlet.model.Adm;
import com.zeta_servlet.model.FlashCard;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/menuFlash")
public class MenuFlash extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                HttpSession session = request.getSession();

                FlashCardDAO flashCardDAO = new FlashCardDAO();
                List<FlashCard> liF;
                int id;
                String nomeAula = request.getParameter("nomeAula");
                if (nomeAula==null){
                    request.setAttribute("nomeAula", session.getAttribute("nomeAula"));
                    id = (int) session.getAttribute("idAula");
                }
                else {
                    request.setAttribute("nomeAula", nomeAula);
                    session.setAttribute("nomeAula", nomeAula);
                    id = Integer.parseInt(request.getParameter("idAula"));
                    session.setAttribute("idAula", id);
                }
                liF = flashCardDAO.buscarPorIdAula(id);
                request.setAttribute("list", liF);
                System.out.println(1 + "menu");
                request.getRequestDispatcher("WEB-INF/jsp/menuFlash.jsp").forward(request, response);


        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace();
            System.out.println(-1);
            request.getRequestDispatcher("WEB-INF/jsp/menuFlash.jsp").forward(request, response);

        }
    }

}
