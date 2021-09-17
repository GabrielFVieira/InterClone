package model;

import aplicacao.Administrador;
import aplicacao.Session;
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

@WebServlet(name = "AdminDAO", urlPatterns = {"/AdminDAO"})
public class AdminDAO extends HttpServlet implements InterfaceBaseDAO<Administrador>{
    private Connection conexao;
    public AdminDAO() {
        try {
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public List<Administrador> listar() {
        List<Administrador> resultado = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from administradores");
            
            while(rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(rs.getInt("id"));
                administrador.setNome(rs.getString("nome"));
                administrador.setCpf(rs.getString("cpf"));
                administrador.setSenha(rs.getString("senha"));
                
                resultado.add(administrador);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar administradores: " + e.getMessage());
        }
        
        return resultado;
    }
    
    @Override
    public void salvar(Administrador administrador)  throws Exception {
        try {   
            String query;
            if(administrador.getId() != null) {
                query = "update administradores set nome=?, cpf=? , senha=? where id = ?";
            } else {
                Validador.validarNovoCPF(administrador.getCpf());
                query = "insert into administradores(nome,cpf,senha) values (?, ?, ?)";
            }
            
            PreparedStatement sql  = conexao.prepareStatement(query);

            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            
            if(administrador.getId() != null) {
                sql.setInt(4, administrador.getId());
            }
            
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
            throw new Exception("Erro ao cadastrar administrador");
        }
    }
    
    @Override
    public Administrador buscarPorId(int id) {
        Administrador administrador = null;
        try {
            String sql = "SELECT * FROM administradores WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                administrador = new Administrador();
                administrador.setId(rs.getInt("id"));
                administrador.setNome(rs.getString("nome"));
                administrador.setCpf(rs.getString("cpf"));
                administrador.setSenha(rs.getString("senha"));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar administrador de id " + id + ": " + e.getMessage());
        }
        return administrador;
    }
    
    public Administrador buscarPorCPF(String cpf) {
        Administrador administrador = null;
        try {
            String sql = "SELECT * FROM administradores WHERE cpf = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                administrador = new Administrador();
                administrador.setId(rs.getInt("id"));
                administrador.setNome(rs.getString("nome"));
                administrador.setCpf(rs.getString("cpf"));
                administrador.setSenha(rs.getString("senha"));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar administrador com cpf " + cpf + ": " + e.getMessage());
        }
        return administrador;
    }
    
    @Override
    public boolean excluir(int id, Session session) throws Exception {
        try {
            String sql = "DELETE FROM administradores WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro ao excluir administrador de id " + id + ": " + e.getMessage());
            throw new Exception("Não foi possível excluir o administrador informado");
        }
    }
}
