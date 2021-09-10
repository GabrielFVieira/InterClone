package model;

import aplicacao.Categoria;
import aplicacao.Conta;
import aplicacao.Lancamento;
import aplicacao.Session;
import aplicacao.Usuario;
import aplicacao.Validador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "LancamentoDAO", urlPatterns = {"/LancamentoDAO"})
public class LancamentoDAO extends HttpServlet implements InterfaceBaseDAO<Lancamento> {
    private Connection conexao;
    private CategoriaDAO categoriaDAO;
    private ContaDAO contaDAO;
    public LancamentoDAO() {
        try {
            conexao = Conexao.criaConexao();
            categoriaDAO = new CategoriaDAO();
            contaDAO = new ContaDAO();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public List<Lancamento> listar() {
        List<Lancamento> resultado = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from lancamentos");
            
            while(rs.next()) {
                Lancamento lancamento = new Lancamento();
                lancamento.setId(rs.getInt("id"));
                lancamento.setValor(rs.getDouble("valor"));
                lancamento.setOperacao(rs.getString("operacao"));
                lancamento.setData(rs.getDate("data").toLocalDate());
                lancamento.setDescricao(rs.getString("descricao"));
                
                lancamento.setConta(contaDAO.buscarPorId(rs.getInt("id_conta")));
                lancamento.setCategoria(
                        categoriaDAO.buscarPorId(rs.getInt("id_categoria")));
                
                resultado.add(lancamento);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar lancamentos: " + e.getMessage());
        }
        
        return resultado;
    }
    
    public List<Lancamento> listar(Integer idUsuario) {
        return listar(idUsuario, null);
    }
    
    public List<Lancamento> listar(Integer idUsuario, Integer idConta) {
        List<Lancamento> resultado = new ArrayList<>();
        try {
            PreparedStatement ps  = conexao.prepareStatement(
                    "SELECT l.* FROM lancamentos l "
                    + "INNER JOIN contas c "
                    + "ON c.id = l.id_conta "
                    + "WHERE c.id_usuario = ? "
                    + (idConta != null ? " AND l.id_conta = ? " : "")
                    + " ORDER BY l.data DESC");
            
            ps.setInt(1, idUsuario);
            if(idConta != null) {
                ps.setInt(2, idConta);
            }
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Lancamento lancamento = new Lancamento();
                lancamento.setId(rs.getInt("id"));
                lancamento.setValor(rs.getDouble("valor"));
                lancamento.setOperacao(rs.getString("operacao"));
                lancamento.setData(rs.getDate("data").toLocalDate());
                lancamento.setDescricao(rs.getString("descricao"));
                
                lancamento.setConta(contaDAO.buscarPorId(rs.getInt("id_conta")));
                lancamento.setCategoria(
                        categoriaDAO.buscarPorId(rs.getInt("id_categoria")));
                
                resultado.add(lancamento);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar lancamentos: " + e.getMessage());
        }
        
        return resultado;
    }
    
    @Override
    public void salvar(Lancamento lancamento) {
        try {
            String query;
            if(lancamento.getId() != null) {
                query = "update lancamentos set id_conta=?, id_categoria=?, valor=?, operacao=?, data=?, descricao=? where id = ?";
            } else {
                query = "insert into lancamentos(id_conta, id_categoria, valor, operacao, data, descricao) values (?, ?, ?, ?, ?, ?)";
            }
                       
            PreparedStatement sql  = conexao.prepareStatement(query);

            sql.setInt(1, lancamento.getConta().getId());
            sql.setInt(2, lancamento.getCategoria().getId());
            sql.setDouble(3, lancamento.getValor());
            sql.setString(4, lancamento.getOperacaoString());
            sql.setDate(5, Date.valueOf(lancamento.getData()));
            sql.setString(6, lancamento.getDescricao());
            
            if(lancamento.getId() != null) {
                sql.setInt(7, lancamento.getId());
            }
            
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar lancamento: " + e.getMessage());
        }
    }
    
    @Override
    public Lancamento buscarPorId(int id) {
        Lancamento lancamento = null;
        try {
            String sql = "select * from lancamentos where id = ?";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                lancamento = new Lancamento();
                lancamento.setId(rs.getInt("id"));
                lancamento.setValor(rs.getDouble("valor"));
                lancamento.setOperacao(rs.getString("operacao"));
                lancamento.setData(rs.getDate("data").toLocalDate());
                lancamento.setDescricao(rs.getString("descricao"));
                
                lancamento.setConta(contaDAO.buscarPorId(rs.getInt("id_conta")));
                lancamento.setCategoria(
                        categoriaDAO.buscarPorId(rs.getInt("id_categoria")));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar lancamento de id " + id + ": " + e.getMessage());
        }
        return  lancamento;
    }
    
    @Override
    public boolean excluir(int id ) {
        try {
            String sql = "DELETE FROM lancamentos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro ao excluir lancamento de id " + id + ": " + e.getMessage());
            return false;
        }
    }
    
    public Double buscarSaldo(Integer idUsuario) {
        return null;
    }
}
