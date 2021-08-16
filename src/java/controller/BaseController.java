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

public abstract class BaseController<T extends Object> extends SessionController {   
    
    protected abstract String getPaginaListagem();
    protected abstract String getPaginaCadastro();
    protected abstract String getAtributoJSP();
    protected abstract String getAtributoListaJSP();
    protected abstract InterfaceBaseDAO<T> getDAO();
    protected abstract T getModeloVazio();
    protected abstract T getModeloNaRequest(HttpServletRequest request);
    protected abstract TipoSessao getTipoSessaoNecessaria();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!verificaSessao(request, response, getTipoSessaoNecessaria())) {
            return;
        }
        
        InterfaceBaseDAO<T> dao = getDAO();
        
        RequestDispatcher rd;
        int id;
        T modelo;
        String acao = (String) request.getParameter("acao");
        
        if(acao == null) {
            redirecionarParaListagem(dao, request, response);
            return;
        }
        
        switch (acao) {
            case "cadastrar":
                modelo = getModeloVazio();
                
                request.setAttribute(getAtributoJSP(), modelo);
                rd = request.getRequestDispatcher(getPaginaCadastro());
                rd.forward(request, response);
                break;
                
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                modelo = dao.buscarPorId(id);
                
                if(modelo != null) {
                    request.setAttribute(getAtributoJSP(), modelo);
                    rd = request.getRequestDispatcher(getPaginaCadastro());
                    rd.forward(request, response);
                } else {
                    System.out.println("Erro ao editar");
                }    
                break;
            
            case "excluir":
                id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
                
                response.sendRedirect(getUrlPattern());
                break;
            
            default:
                redirecionarParaListagem(dao, request, response);
                break;
        }
    }
    
    private void redirecionarParaListagem(InterfaceBaseDAO<T> dao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(getAtributoListaJSP(), dao.listar());
        RequestDispatcher rd = request.getRequestDispatcher(getPaginaListagem());
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!verificaSessao(request, response)) {
            return;
        }
        
        T modelo = getModeloNaRequest(request);
        
        InterfaceBaseDAO<T> dao = getDAO();
        dao.salvar(modelo);
        
        response.sendRedirect(getUrlPattern());
    }    
    
    protected String getUrlPattern() {
        String urlPattern = this.getClass().getAnnotation(WebServlet.class).urlPatterns()[0];
        return urlPattern.replace("/", "");
    }
}
