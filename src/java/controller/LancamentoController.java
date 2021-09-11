package controller;

import aplicacao.Categoria;
import aplicacao.Conta;
import aplicacao.TipoSessao;
import aplicacao.Lancamento;
import aplicacao.Session;
import aplicacao.TipoOperacao;
import aplicacao.Validador;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;
import model.ContaDAO;
import model.LancamentoDAO;
import model.InterfaceBaseDAO;

@WebServlet(urlPatterns = {"/lancamentos"})
public class LancamentoController extends BaseController<Lancamento> {   

    @Override
    protected TipoSessao getTipoSessaoNecessaria() {
        return TipoSessao.USUARIO;
    } 
    
    @Override
    protected String getPaginaListagem() {
        return "/externo/listaLancamentos.jsp";
    }

    @Override
    protected String getPaginaCadastro() {
        return "/externo/cadastroLancamentos.jsp";
    }

    @Override
    protected String getAtributoJSP() {
        return "lancamento";
    }

    @Override
    protected String getAtributoListaJSP() {
        return "lancamentos";
    }

    @Override
    protected InterfaceBaseDAO<Lancamento> getDAO() {
        return new LancamentoDAO();
    }

    @Override
    protected Lancamento getModeloVazio() {
        Lancamento lancamento = new Lancamento();
        lancamento.setData(LocalDate.now());
        
        return lancamento;
    }

    @Override
    protected Lancamento getModeloNaRequest(HttpServletRequest request) {
        Lancamento lancamento = new Lancamento();
        
        if(request.getParameter("id") != null) {
           lancamento.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        if(request.getParameter("account") != null) {
            Conta conta = new Conta();
            conta.setId(Integer.parseInt(request.getParameter("account")));
            
            lancamento.setConta(conta);
        }
        
        if(request.getParameter("category") != null) {
            Categoria categoria = new Categoria();
            categoria.setId(Integer.parseInt(request.getParameter("category")));
            lancamento.setCategoria(categoria);
        }
        
        if(request.getParameter("value") != null) {
            lancamento.setValor(Double.parseDouble(request.getParameter("value").replace(".", "").replace(",", ".")));
        }
        
        lancamento.setOperacao(request.getParameter("operation"));
        lancamento.setData(LocalDate.parse(request.getParameter("date")));
        lancamento.setDescricao(request.getParameter("description"));
        
        return lancamento;
    }

    @Override
    protected Map<String, String> validarModelo(Lancamento modelo, Session session) {
            Map<String, String> erros = new HashMap<>();
            
            try {
                if(modelo.getConta() == null) {
                    throw new IllegalArgumentException(Validador.MSG_CAMPO_OBRIGATORIO);
                }
                
                ContaDAO contaDAO = new ContaDAO();
                Conta conta = contaDAO.buscarPorId(modelo.getConta().getId());
            
                if(conta == null || !conta.getUsuario().getId().equals(session.getIdUsuario())) {
                    throw new IllegalArgumentException("Conta informada não pertence ao usuário logado");
                }
            } catch (Exception err) {
                erros.put("account", err.getMessage());  
            }
            
            try {
                if(modelo.getCategoria()== null) {
                    throw new IllegalArgumentException(Validador.MSG_CAMPO_OBRIGATORIO);
                }
                
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.buscarPorId(modelo.getCategoria().getId());
            
                if(categoria == null) {
                    throw new IllegalArgumentException("Categoria informada não existe");
                }
            } catch (Exception err) {
                erros.put("category", err.getMessage());  
            }            
            
            try {
                Validador.validarCampoTexto(modelo.getDescricao(), null, 100, false);
            } catch (Exception err) {
                erros.put("description", err.getMessage());           
            }
            
            try {
                Validador.validarCampoNumerico(modelo.getValor(), false);
            } catch (Exception err) {
                erros.put("value", err.getMessage());           
            }
            
            if(modelo.getOperacao() == null) {
                erros.put("operation", Validador.MSG_CAMPO_OBRIGATORIO);
            }
            
            if(modelo.getConta()== null) {
                erros.put("account", Validador.MSG_CAMPO_OBRIGATORIO);
            }
            
            if(modelo.getCategoria()== null) {
                erros.put("category", Validador.MSG_CAMPO_OBRIGATORIO);
            }
                 
            if(modelo.getData()== null) {
                erros.put("date", Validador.MSG_CAMPO_OBRIGATORIO);
            }
                 
            return erros;
    }
    
    @Override
    protected void redirecionarParaListagem(InterfaceBaseDAO<Lancamento> dao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LancamentoDAO lancamentoDAO = (LancamentoDAO) dao;
        Session sessao = buscarSessao(request);
        
        Integer idConta = null;
        if(request.getParameter("conta") != null) {
            idConta = Integer.parseInt(request.getParameter("conta"));
        }  
        
        List<Lancamento> lancamentos = lancamentoDAO.listar(sessao.getIdUsuario(), idConta);
        
        Double saldo = 0.0;      
        Map<LocalDate, List<Lancamento>> mapaLancamentos = new LinkedHashMap<>();
        for (Lancamento lancamento : lancamentos) {
            if(TipoOperacao.CREDITO.equals(lancamento.getOperacao())) {
                saldo += lancamento.getValor();
            } else {
                saldo -= lancamento.getValor();
            }
            
            List<Lancamento> lancamentosData = mapaLancamentos.containsKey(lancamento.getData()) ?
                    mapaLancamentos.get(lancamento.getData()) : new ArrayList<>();
                    
            lancamentosData.add(lancamento);
            mapaLancamentos.put(lancamento.getData(), lancamentosData);
        }
        
        request.setAttribute("saldo", saldo);
        request.setAttribute(getAtributoListaJSP(), mapaLancamentos);
        request.setAttribute("contas", new ContaDAO().listar(sessao.getIdUsuario()));
        RequestDispatcher rd = request.getRequestDispatcher(getPaginaListagem());
        rd.forward(request, response);
    }
    
    @Override
    protected void redirecionarParaCadastro(HttpServletRequest req, HttpServletResponse resp, Lancamento modelo, Map<String, String> erros)  throws ServletException, IOException {
        if(erros != null) {
            req.setAttribute(Validador.ERROS, erros);
        }

        req.setAttribute(getAtributoJSP(), modelo);
        req.setAttribute("categorias", new CategoriaDAO().listar());
        req.setAttribute("contas", new ContaDAO().listar(buscarSessao(req).getIdUsuario()));
        RequestDispatcher rd = req.getRequestDispatcher(getPaginaCadastro());
        rd.forward(req, resp);
    }
}
