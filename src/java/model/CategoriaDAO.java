package model;

import aplicacao.Categoria;
import aplicacao.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "CategoriaDAO", urlPatterns = {"/CategoriaDAO"})
public class CategoriaDAO extends HttpServlet implements InterfaceBaseDAO<Categoria>{
    private Connection conexao;
    public CategoriaDAO() {
        try {
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public List<Categoria> listar() {
        List<Categoria> resultado = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from categorias");
            
            while(rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                
                resultado.add(categoria);
            } 
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        
        return resultado;
    }
    
    @Override
    public void salvar(Categoria categoria) throws Exception {
        try {
            
            String query;
            if(categoria.getId() != null) {
                query = "update categorias set descricao=? where id = ?";
            } else {
                if(buscarPorDescricao(categoria.getDescricao()) != null) {
                    throw new Exception("Categoria já cadastrada");
                }
                
                query = "insert into categorias(descricao) values (?)";
            }
            
            PreparedStatement sql  = conexao.prepareStatement(query);

            sql.setString(1, categoria.getDescricao());
            
            if(categoria.getId() != null) {
                sql.setInt(2, categoria.getId());
            }
            
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
            throw new Exception("Erro ao cadastrar categoria");
        }
    }
    
    @Override
    public Categoria buscarPorId(int id) {
        Categoria categoria = null;
        try {
            String sql = "SELECT * FROM categorias WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar categoria de id " + id + ": " + e.getMessage());
        }
        return categoria;
    }
           
    public Categoria buscarPorDescricao(String descricao) {
        Categoria categoria = null;
        try {
            String sql = "SELECT * FROM categorias WHERE descricao = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, descricao);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar categoria " + descricao + ": " + e.getMessage());
        }
        return categoria;
    }
    
    @Override
    public boolean excluir(int id, Session session) throws Exception {
        if(existeLancamento(id)) {
            throw new Exception("Não é possível excluir a categoria informada, existem lançamentos vinculados a ela.");
        }
                
        try {
            String sql = "DELETE FROM categorias WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro ao excluir categoria de id " + id + ": " + e.getMessage());
            throw new Exception("Não foi possível excluir a categoria informada");
        }
    }
    
    private boolean existeLancamento(int id) throws Exception {
        try {
            String sql = "select 1 from lancamentos where id_categoria = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                return true;
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar lançamentos com id_conta = " + id + ": " + e.getMessage());
            throw new Exception("Não foi possível verificar os lançamentos da conta informada");
        }
        
        return false;
    }
}
