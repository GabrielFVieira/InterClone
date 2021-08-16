package controller;

import aplicacao.Categoria;
import aplicacao.TipoSessao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
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
