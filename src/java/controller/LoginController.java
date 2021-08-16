package controller;

import aplicacao.Session;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SessionDAO;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {        
            SessionDAO sessionDAO = new SessionDAO();
        
            String cpf = req.getParameter("cpf");
            String senha = req.getParameter("password");
        
            Session session = sessionDAO.logar(cpf, senha);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("session", session);
            
            RequestDispatcher rd = req.getRequestDispatcher("/interno/dashboard.jsp");
            resp.sendRedirect("dashboard");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
        }
    }
    
    
}
