package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import aplicacao.Session;
import aplicacao.TipoSessao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SessionDAO;

/**
 *
 * @author Gabriel
 */
@WebServlet(urlPatterns = {"/session"})
public class SessionController extends HttpServlet {
    public boolean verificaSessao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return this.verificaSessao(request, response, null);
    }
    
    public boolean verificaSessao(HttpServletRequest request, HttpServletResponse response, TipoSessao tipoSessao) throws IOException {
        HttpSession session = request.getSession();
        
        if(session.getAttribute("session") != null) {
            Session sessao = (Session) session.getAttribute("session");
            
            if(tipoSessao != null && !tipoSessao.equals(sessao.getTipo())) {
                response.sendRedirect("dashboard");
                return false;
            }
        } else {
            response.sendRedirect("login");
            return false;
        }
        
        return true;
    }
}
