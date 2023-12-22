package dao;


import config.ConexaoBancoDado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Login;


import org.apache.log4j.Logger;


public class LoginDao {
    
    static Logger logger = Logger.getLogger(LoginDao.class);

	private Connection con = null;
	private Statement smt = null;
        private ResultSet rs = null;
    
     public List<Login> buscarLogin(String login, String senha){
            try {
              con  = new ConexaoBancoDado().connectionPostgreSQL();
              String query ="select l.login, l.senha from loginusuario l inner join usuario p on l.idusuario = p.id where l.login ='"+login+"' and l.senha ='"+senha+"'";
              List<Login> usuario = new ArrayList<>();
              PreparedStatement ps = con.prepareStatement(query);
              rs = ps.executeQuery();
              while (rs.next()) {
                Login lg = new Login(); 
                lg.setLogin(rs.getString("login"));
                lg.setSenha(rs.getString("senha"));
                usuario.add(lg);
              }
                
              return usuario;
		
            } catch (Exception e) {
                logger.info( e.getClass().getName() + " : " + e.getMessage() );
            }
            //con.close();
            return null;
        }

}
