package controller;

import aplicacao.TipoSessao;
import aplicacao.Usuario;
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
import model.UsuarioDAO;
import model.InterfaceBaseDAO;

@WebServlet(urlPatterns = {"/usuarios"})
public class UsuarioController extends BaseController<Usuario> {   

    @Override
    protected TipoSessao getTipoSessaoNecessaria() {
        return TipoSessao.ADMNISTRADOR;
    } 
    
    @Override
    protected String getPaginaListagem() {
        return "/interno/listaUsuarios.jsp";
    }

    @Override
    protected String getPaginaCadastro() {
        return "/interno/cadastroUsuario.jsp";
    }

    @Override
    protected String getAtributoJSP() {
        return "usuario";
    }

    @Override
    protected String getAtributoListaJSP() {
        return "usuarios";
    }

    @Override
    protected InterfaceBaseDAO<Usuario> getDAO() {
        return new UsuarioDAO();
    }

    @Override
    protected Usuario getModeloVazio() {
        Usuario usuario = new Usuario();
        usuario.setNome("");
        usuario.setCpf("");
        usuario.setSenha("");
        usuario.setSuspenso(false);
        
        return usuario;
    }

    @Override
    protected Usuario getModeloNaRequest(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        
        if(request.getParameter("id") != null) {
           usuario.setId(Integer.parseInt(request.getParameter("id")));
        }
        usuario.setNome(request.getParameter("name"));
        usuario.setCpf(request.getParameter("cpf"));
        usuario.setSenha(request.getParameter("password"));
        usuario.setSuspenso(Boolean.valueOf(request.getParameter("suspended")));

        return usuario;
    }

}
