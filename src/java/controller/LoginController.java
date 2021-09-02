package controller;

import aplicacao.Session;
import aplicacao.Validador;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        redirecionar(request, response, null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
            SessionDAO sessionDAO = new SessionDAO();
            Map<String, String> erros = new HashMap<>();
            
            String cpf = req.getParameter("cpf");
            try {
                Validador.validarCPF(cpf);
            } catch (Exception err) {
                erros.put("cpf", err.getMessage());           
            }
            
            String senha = req.getParameter("password");
            try {
                Validador.validarSenha(senha);
            } catch (Exception err) {
                erros.put("password", err.getMessage());
            }
            
            if(!erros.isEmpty()) {
                redirecionar(req, resp, erros);
                return;
            }
            
            try {
                Session session = sessionDAO.logar(cpf, senha);
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("session", session);

                resp.sendRedirect("dashboard");
            } catch (Exception err) {
                erros.put(Validador.ALERTA, err.getMessage());
                redirecionar(req, resp, erros);
            }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, Map<String, String> erros)  throws ServletException, IOException {
        if(erros != null) {
            req.setAttribute(Validador.ERROS, erros);
        }
        RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
        rd.forward(req, resp);
    }
    
    
}
