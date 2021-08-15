package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import aplicacao.Administrador;
import aplicacao.TipoSessao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdminDAO;
import model.InterfaceBaseDAO;

@WebServlet(urlPatterns = {"/administradores"})
public class AdminController extends BaseController<Administrador> {   

    @Override
    protected TipoSessao getTipoSessaoNecessaria() {
        return TipoSessao.ADMNISTRADOR;
    } 
    
    @Override
    protected String getPaginaListagem() {
        return "/interno/listaAdmins.jsp";
    }

    @Override
    protected String getPaginaCadastro() {
        return "/interno/cadastroAdmin.jsp";
    }

    @Override
    protected String getAtributoJSP() {
        return "admin";
    }

    @Override
    protected String getAtributoListaJSP() {
        return "administradores";
    }

    @Override
    protected InterfaceBaseDAO<Administrador> getDAO() {
        return new AdminDAO();
    }

    @Override
    protected Administrador getModeloVazio() {
        Administrador admin = new Administrador();
        admin.setNome("");
        admin.setCpf("");
        admin.setSenha("");
        
        return admin;
    }

    @Override
    protected Administrador getModeloNaRequest(HttpServletRequest request) {
        Administrador admin = new Administrador();
        
        if(request.getParameter("id") != null) {
           admin.setId(Integer.parseInt(request.getParameter("id")));
        }
        admin.setNome(request.getParameter("name"));
        admin.setCpf(request.getParameter("cpf"));
        admin.setSenha(request.getParameter("password"));

        return admin;
    }

}
