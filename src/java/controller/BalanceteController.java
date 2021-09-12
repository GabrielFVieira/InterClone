package controller;

import aplicacao.Lancamento;
import aplicacao.Session;
import aplicacao.TipoSessao;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaDAO;
import model.LancamentoDAO;

@WebServlet(urlPatterns = {"/balancete"})
public class BalanceteController extends SessionController {   

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!verificaSessao(req, resp, TipoSessao.USUARIO)) {
            return;
        }
        Session sessao = buscarSessao(req);
                
        Integer idConta = null;
        if(req.getParameter("conta") != null) {
            idConta = Integer.parseInt(req.getParameter("conta"));
        }  
        
        List<Lancamento> lancamentos = new LancamentoDAO().listar(sessao.getIdUsuario(), idConta);
        Map<String, Double> dados = new LinkedHashMap<>();
        for (Lancamento lancamento : lancamentos) {
            String categoria = lancamento.getCategoria().getDescricao();
            Double valorCategoria = dados.containsKey(categoria) ?
                                    dados.get(categoria) : 0;
            
            valorCategoria += lancamento.getValor();
            dados.put(categoria, valorCategoria);
        }
        
        Map<String, Double> dadosOrdenados = new LinkedHashMap<>();
        dados.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(x -> dadosOrdenados.put(x.getKey(), x.getValue()));
        
        req.setAttribute("dados", dadosOrdenados);
        req.setAttribute("contas", new ContaDAO().listar(sessao.getIdUsuario()));
        RequestDispatcher rd = req.getRequestDispatcher("/externo/balancete.jsp");
        rd.forward(req, resp);
    }
}
