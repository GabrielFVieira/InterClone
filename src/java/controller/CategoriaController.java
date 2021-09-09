package controller;

import aplicacao.Categoria;
import aplicacao.Session;
import aplicacao.TipoSessao;
import aplicacao.Validador;
import java.util.HashMap;
import java.util.Map;
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

    @Override
    protected Map<String, String> validarModelo(Categoria modelo, Session session) {
        Map<String, String> erros = new HashMap<>();

        try {
            Validador.validarCampoTexto(modelo.getDescricao(), null, 20);
        } catch (Exception err) {
            erros.put("description", err.getMessage());           
        }

        return erros;
    }
}
