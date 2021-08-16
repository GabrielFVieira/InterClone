package controller;

import aplicacao.Session;
import aplicacao.TipoSessao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/dashboard"})
public class DashboardController extends SessionController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(super.verificaSessao(request, response)) {        
            HttpSession httpSession = request.getSession();
        
            Session sessao = (Session) httpSession.getAttribute("session");            
            String pagina = TipoSessao.ADMNISTRADOR.equals(sessao.getTipo()) ? "/interno/dashboard.jsp" : "/externo/dashboard.jsp";
            
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        }
    }
}
