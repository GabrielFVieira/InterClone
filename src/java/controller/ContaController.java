package controller;

import aplicacao.TipoSessao;
import aplicacao.Conta;
import aplicacao.Session;
import aplicacao.Usuario;
import aplicacao.Validador;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaDAO;
import model.InterfaceBaseDAO;
import model.UsuarioDAO;

@WebServlet(urlPatterns = {"/contas"})
public class ContaController extends BaseController<Conta> {   

    @Override
    protected TipoSessao getTipoSessaoNecessaria() {
        return TipoSessao.USUARIO;
    } 
    
    @Override
    protected String getPaginaListagem() {
        return "/externo/listaContas.jsp";
    }

    @Override
    protected String getPaginaCadastro() {
        return "/externo/cadastroConta.jsp";
    }

    @Override
    protected String getAtributoJSP() {
        return "conta";
    }

    @Override
    protected String getAtributoListaJSP() {
        return "contas";
    }

    @Override
    protected InterfaceBaseDAO<Conta> getDAO() {
        return new ContaDAO();
    }

    @Override
    protected Conta getModeloVazio() {
        Conta conta = new Conta();
        conta.setNome("");
        conta.setAgencia("");
        conta.setBanco("");
        conta.setContaCorrente("");
        
        return conta;
    }

    @Override
    protected Conta getModeloNaRequest(HttpServletRequest request) throws Exception {
        Conta conta = new Conta();
        
        if(request.getParameter("id") != null) {
           conta.setId(Integer.parseInt(request.getParameter("id")));
        }
        conta.setNome(request.getParameter("name"));
        conta.setAgencia(request.getParameter("agency"));
        conta.setBanco(request.getParameter("bank"));
        conta.setContaCorrente(request.getParameter("account"));
        
        Session session = buscarSessao(request);
        if(session == null) {
            throw new Exception("Sessão inválida");
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogado = usuarioDAO.buscarPorId(session.getIdUsuario());

        if(usuarioLogado == null) {
            throw new Exception("Nenhum usuário logado");
        }

        conta.setUsuario(usuarioLogado);
        
        return conta;
    }

    @Override
    protected Map<String, String> validarModelo(Conta modelo, Session session) {
            Map<String, String> erros = new HashMap<>();
            
            try {
                Validador.validarCampoTexto(modelo.getNome(), null, 20);
            } catch (Exception err) {
                erros.put("name", err.getMessage());           
            }
            
            try {
                Validador.validarCampoTexto(modelo.getAgencia(), null, 6);
            } catch (Exception err) {
                erros.put("agency", err.getMessage());           
            }
                        
            try {
                Validador.validarCampoTexto(modelo.getBanco(), 3, 3);
            } catch (Exception err) {
                erros.put("bank", err.getMessage());           
            }
                                    
            try {
                Validador.validarCampoTexto(modelo.getContaCorrente(), null, 6);
            } catch (Exception err) {
                erros.put("account", err.getMessage());           
            }
                        
            return erros;
    }
    
    @Override
    protected void redirecionarParaListagem(InterfaceBaseDAO<Conta> dao, HttpServletRequest request, HttpServletResponse response, String erro) throws ServletException, IOException {
        ContaDAO contaDAO = (ContaDAO) dao;
        
        if(erro != null && !erro.isEmpty()) {
            Map<String, String> erros = new HashMap<>();
            erros.put(Validador.ALERTA, erro);
            
            request.setAttribute(Validador.ERROS, erros);
        }
        
        request.setAttribute(getAtributoListaJSP(), contaDAO.listar(buscarSessao(request).getIdUsuario()));
        RequestDispatcher rd = request.getRequestDispatcher(getPaginaListagem());
        rd.forward(request, response);
    }
}
