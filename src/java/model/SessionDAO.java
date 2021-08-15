package model;

import aplicacao.Session;
import aplicacao.TipoSessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "SessionDAO", urlPatterns = {"/SessionDAO"})
public class SessionDAO extends HttpServlet {
    private Connection conexao;
    public SessionDAO() {
        try {
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Session logar(String cpf, String senha) throws Exception {
        Session session = logarPorTipo(cpf, senha, TipoSessao.USUARIO);
        if(session == null) {
            session = logarPorTipo(cpf, senha, TipoSessao.ADMNISTRADOR);
        }
        
        if(session == null) {
            throw new Exception("Credenciais incorretas");
        }
        
        return session;
    }
    
    private Session logarPorTipo(String cpf, String senha, TipoSessao tipo) {
        try {
            String sql = TipoSessao.ADMNISTRADOR.equals(tipo) ? 
                    "SELECT nome FROM administradores WHERE cpf = ? and senha = ?" :
                    "SELECT nome FROM usuarios WHERE cpf = ? and senha = ?";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Session session = new Session();
                session.setNomeUsuario(rs.getString("nome"));
                session.setTipo(tipo);
                
                return session;
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro ao logar usuário " + cpf + ": " + e.getMessage());
        }
        
        return null;
    }
}
