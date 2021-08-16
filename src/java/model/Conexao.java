package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})
public class Conexao extends HttpServlet {

    private static Connection conexao = null;
    
    public static Connection criaConexao() throws SQLException {
        if(conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/financeiro", "root", "");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Não foi possível encontrar o Driver");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Não foi possível conectar ao banco");
            }
        }
        
        return conexao;
    }
    
}
