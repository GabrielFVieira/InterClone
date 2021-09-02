package controller;

import aplicacao.Administrador;
import aplicacao.TipoSessao;
import aplicacao.Validador;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
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

    @Override
    protected Map<String, String> validarModelo(Administrador modelo) {
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
