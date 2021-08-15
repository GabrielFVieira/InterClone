package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import aplicacao.Administrador;
import aplicacao.Categoria;
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
import model.CategoriaDAO;
import model.InterfaceBaseDAO;

@WebServlet(urlPatterns = {"/categorias"})
public class CategoriaController extends BaseController<Categoria> {   

    @Override
    protected TipoSessao getTipoSessaoNecessaria() {
        return TipoSessao.ADMNISTRADOR;
    } 
    
    @Override
    protected String getPaginaListagem() {
        return "/interno/listaCategorias.jsp";
    }

    @Override
    protected String getPaginaCadastro() {
        return "/interno/cadastroCategoria.jsp";
    }

    @Override
    protected String getAtributoJSP() {
        return "categoria";
    }

    @Override
    protected String getAtributoListaJSP() {
        return "categorias";
    }

    @Override
    protected InterfaceBaseDAO<Categoria> getDAO() {
        return new CategoriaDAO();
    }

    @Override
    protected Categoria getModeloVazio() {
        Categoria categoria = new Categoria();
        categoria.setDescricao("");
        return categoria;
    }

    @Override
    protected Categoria getModeloNaRequest(HttpServletRequest request) {
        Categoria categoria = new Categoria();
        
        if(request.getParameter("id") != null) {
           categoria.setId(Integer.parseInt(request.getParameter("id")));
        }
        categoria.setDescricao(request.getParameter("description"));
        
        return categoria;
    }

}
