package dao;

import config.ConexaoBancoDado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import config.Config;
import model.Loja;

public class LojaDao {

	static Logger logger = Logger.getLogger(LojaDao.class);

	private Connection con = null;
	private Statement smt = null;
	
	
	private Config Config = new Config();

	public void salvar(Loja loja){
		try {
			
			logger.info("Salvando informcao da nova loja");

			 con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "insert into loja (id, nome, endereco, bairro,cep) values(" 
					+ String.valueOf(loja.getId()) + ", "
					+ "'" + loja.getNome() + "',"
					+ "'" + loja.getEndereco() + "',"
					+ "'" + loja.getBairro()+ "',"
					+ "'" + loja.getCep()+ "');";

			smt.executeUpdate(sql);
			con.close();

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + " : " + e.getMessage() );
			System.exit(0);
		}
	}

	public void editar(Loja loja){
		try {
			
			logger.info("Editar informcao da loja");
			
			 con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "update loja set "
					+ " nome='"+ loja.getNome() + "',"
					+ " endereco='" + loja.getEndereco() + "',"
					+ " bairro='"+ loja.getBairro() + "', "
					+ " cep = " + loja.getCep() + " "
					+ " where id=" + loja.getId() + ";";

			smt.executeUpdate(sql);
			con.close();

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + " : " + e.getMessage() );
			System.exit(0);
		}
	}

	public void update(Loja loja){
		try {

			logger.info("Atualizando informacao da loja");
			
			 con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "update loja set "
					+ "nome='"+ loja.getNome() + "', "
					+ "endereco='" + loja.getEndereco() + "', "
					+ "bairro='"+ loja.getBairro() + "', "
					+ "cep = " + loja.getCep() + ";";
			smt.executeUpdate(sql);
			con.close();

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + " : " + e.getMessage() );
			System.exit(0);
		}
	}

	public List<Loja> listar(){
		try{
			logger.info("Lista de todas as lojas");
			
			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "select * from loja";
			ResultSet rs = smt.executeQuery(sql);
			List<Loja> listLoja = new ArrayList<>();
			while( rs.next() ) {
				Loja loja = new Loja();
				loja.setId(rs.getInt(1));
				loja.setNome(rs.getString(2));
				loja.setEndereco(rs.getString(3));
				loja.setBairro(rs.getString(4));
				loja.setCep(rs.getString(5));
				listLoja.add(loja);
			}
			con.close();
			return listLoja;

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + " : " + e.getMessage() );
			System.exit(0);
		}

		return Collections.emptyList();
	}

	public void excluir(int id){
		try{
			logger.info("Excluindo loja");
			
			 con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "DELETE FROM loja WHERE id=" + id + ";";
			smt.executeUpdate(sql);
			con.close();
		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + " : " + e.getMessage() );
			System.exit(0);
		}
	}
}