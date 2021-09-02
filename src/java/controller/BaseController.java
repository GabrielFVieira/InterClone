package controller;

import aplicacao.TipoSessao;
import aplicacao.Validador;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected abstract Map<String, String> validarModelo(T modelo);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!verificaSessao(request, response, getTipoSessaoNecessaria())) {
            return;
        }
        
        InterfaceBaseDAO<T> dao = getDAO();
        
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
                redirecionarParaCadastro(request, response, modelo, null);
                break;
                
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                modelo = dao.buscarPorId(id);
                
                if(modelo != null) {
                    redirecionarParaCadastro(request, response, modelo, null);
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
        
        Map<String, String> erros = validarModelo(modelo);
        if(erros != null && !erros.isEmpty()) {
            redirecionarParaCadastro(request, response, modelo, erros);
            return;
        }
        
        try {
            InterfaceBaseDAO<T> dao = getDAO();
            dao.salvar(modelo);

            response.sendRedirect(getUrlPattern());
        } catch (Exception err) {
            erros.put(Validador.ALERTA, err.getMessage());
            redirecionarParaCadastro(request, response, modelo, erros);
        }
    }
    
    protected void redirecionarParaCadastro(HttpServletRequest req, HttpServletResponse resp, T modelo, Map<String, String> erros)  throws ServletException, IOException {
        if(erros != null) {
            req.setAttribute(Validador.ERROS, erros);
        }
        
        req.setAttribute(getAtributoJSP(), modelo);
        RequestDispatcher rd = req.getRequestDispatcher(getPaginaCadastro());
        rd.forward(req, resp);
    }
    
    protected String getUrlPattern() {
        String urlPattern = this.getClass().getAnnotation(WebServlet.class).urlPatterns()[0];
        return urlPattern.replace("/", "");
    }
}
