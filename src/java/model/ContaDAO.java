package model;

import aplicacao.Conta;
import aplicacao.Session;
import aplicacao.Usuario;
import aplicacao.Validador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ContaDAO", urlPatterns = {"/ContaDAO"})
public class ContaDAO extends HttpServlet implements InterfaceBaseDAO<Conta> {
    private Connection conexao;
    private UsuarioDAO usuarioDAO;
    public ContaDAO() {
        try {
            conexao = Conexao.criaConexao();
            usuarioDAO = new UsuarioDAO();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public List<Conta> listar() {
        List<Conta> resultado = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from contas");
            
            while(rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("nome_conta"));
                conta.setBanco(rs.getString("banco"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setContaCorrente(rs.getString("conta_corrente"));
                
                conta.setUsuario(usuarioDAO.buscarPorId(rs.getInt("id_usuario")));
                resultado.add(conta);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar contas: " + e.getMessage());
        }
        
        return resultado;
    }
    
    public List<Conta> listar(Integer idUsuario) {
        List<Conta> resultado = new ArrayList<>();
        try {
            PreparedStatement ps  = conexao.prepareStatement("select * from contas where id_usuario=?");
            
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("nome_conta"));
                conta.setBanco(rs.getString("banco"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setContaCorrente(rs.getString("conta_corrente"));
                
                conta.setUsuario(usuarioDAO.buscarPorId(rs.getInt("id_usuario")));
                resultado.add(conta);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar contas: " + e.getMessage());
        }
        
        return resultado;
    }
    
    @Override
    public void salvar(Conta conta) {
        try {
            String query;
            if(conta.getId() != null) {
                query = "update contas set nome_conta=?, banco=?, agencia=?, conta_corrente=? where id = ?";
            } else {
                query = "insert into contas(nome_conta,banco,agencia,conta_corrente, id_usuario) values (?, ?, ?, ?)";
            }
                       
            PreparedStatement sql  = conexao.prepareStatement(query);

            sql.setString(1, conta.getNome());
            sql.setString(2, conta.getBanco());
            sql.setString(3, conta.getAgencia());
            sql.setString(4, conta.getContaCorrente());
            
            if(conta.getId() != null) {
                sql.setInt(5, conta.getId());
            } else {
                sql.setInt(5, conta.getUsuario().getId());
            }
            
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }
    }
    
    @Override
    public Conta buscarPorId(int id) {
        Conta conta = null;
        try {
            String sql = "select * from contas where id = ?";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("nome_conta"));
                conta.setBanco(rs.getString("banco"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setContaCorrente(rs.getString("conta_corrente"));
                
                conta.setUsuario(usuarioDAO.buscarPorId(rs.getInt("id_usuario")));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar conta de id " + id + ": " + e.getMessage());
        }
        return conta;
    }
    
    @Override
    public boolean excluir(int id ) {
        try {
            String sql = "DELETE FROM contas WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro ao excluir conta de id " + id + ": " + e.getMessage());
            return false;
        }
    }
}
