package dao;

import config.ConexaoBancoDado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import config.Config;
import model.Rito;

public class RitoDao {
	
	static Logger logger = Logger.getLogger(RitoDao.class);
	
	private Connection con = null;
	private Statement smt = null;
	private Config config = new Config();

	public List<Rito> listar(){
		try{
			logger.info("Lista de todas as ritos");
			
			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "select * from rito";
			ResultSet rs = smt.executeQuery(sql);
			List<Rito> list = new ArrayList<>();
			while( rs.next() ) {
				Rito rito = new Rito();
				rito.setId(rs.getInt(1));
				rito.setNome(rs.getString(2));
				list.add(rito);
			}
			con.close();
			return list;

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		return Collections.emptyList();
	}

}
