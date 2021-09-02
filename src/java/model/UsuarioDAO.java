package model;

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

@WebServlet(name = "UsuarioDAO", urlPatterns = {"/UsuarioDAO"})
public class UsuarioDAO extends HttpServlet implements InterfaceBaseDAO<Usuario> {
    private Connection conexao;
    public UsuarioDAO() {
        try {
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from usuarios");
            
            while(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSuspenso(rs.getString("suspenso").toCharArray()[0]);
                
                resultado.add(usuario);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        
        return resultado;
    }
    
    @Override
    public void salvar(Usuario usuario) {
        try {
            Validador.validarNovoCPF(usuario.getCpf());
            
            String query;
            if(usuario.getId() != null) {
                query = "update usuarios set nome=?, cpf=? , senha=?, suspenso=? where id = ?";
            } else {
                query = "insert into usuarios(nome,cpf,senha,suspenso) values (?, ?, ?, ?)";
            }
                       
            PreparedStatement sql  = conexao.prepareStatement(query);

            sql.setString(1, usuario.getNome());
            sql.setString(2, usuario.getCpf());
            sql.setString(3, usuario.getSenha());
            sql.setString(4, usuario.getSuspenso().toString());
            
            if(usuario.getId() != null) {
                sql.setInt(5, usuario.getId());
            }
            
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    
    @Override
    public Usuario buscarPorId(int id) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSuspenso(rs.getString("suspenso").toCharArray()[0]);
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar usuário de id " + id + ": " + e.getMessage());
        }
        return usuario;
    }
    
    public Usuario buscarPorCPF(String cpf) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM usuarios WHERE cpf = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSuspenso(rs.getString("suspenso").toCharArray()[0]);
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar usuário com cpf " + cpf + ": " + e.getMessage());
        }
        return usuario;
    }
    
    @Override
    public boolean excluir(int id ) {
        try {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro ao excluir usuário de id " + id + ": " + e.getMessage());
            return false;
        }
    }
}
