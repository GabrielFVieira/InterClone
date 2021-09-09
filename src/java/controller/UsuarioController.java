package controller;

import aplicacao.Session;
import aplicacao.TipoSessao;
import aplicacao.Usuario;
import aplicacao.Validador;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
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

    @Override
    protected Map<String, String> validarModelo(Usuario modelo, Session session) {
            Map<String, String> erros = new HashMap<>();
            
            try {
                Validador.validarCPF(modelo.getCpf());
            } catch (Exception err) {
                erros.put("cpf", err.getMessage());           
            }
            
            try {
                Validador.validarSenha(modelo.getSenha());
            } catch (Exception err) {
                erros.put("password", err.getMessage());           
            }
            
            try {
                Validador.validarCampoTexto(modelo.getNome(), null, 20);
            } catch (Exception err) {
                erros.put("name", err.getMessage());           
            }
                        
            return erros;
    }
}
