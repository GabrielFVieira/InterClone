package model;

import aplicacao.Categoria;
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
    public void salvar(Categoria categoria) {
        try {
            String query;
            if(categoria.getId() != null) {
                query = "update categorias set descricao=? where id = ?";
            } else {
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
        }
    }
    
    @Override
    public Categoria buscarPorId(int id) {
        Categoria categoria = new Categoria();
        try {
            String sql = "SELECT * FROM categorias WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao buscar categoria de id " + id + ": " + e.getMessage());
        }
        return categoria;
    }
    
    @Override
    public boolean excluir(int id ) {
        try {
            String sql = "DELETE FROM categorias WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro ao excluir categoria de id " + id + ": " + e.getMessage());
            return false;
        }
    }
}
