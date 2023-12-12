package dao;

import config.ConexaoBancoDado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import model.Potencia;

public class PotenciaDao {
	
	static Logger logger = Logger.getLogger(PotenciaDao.class);
	
	private Connection con = null;
	private Statement smt = null;

	/*
	public void salvar(PessoaModel pessoa){

		try {
			
			logger.info("Salvando informcao da nova potencia");

			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "insert into pessoa (id, nome, endereco, bairro,cep) values(" 
					+ String.valueOf(pessoa.getId()) + ", "
					+ "'" + pessoa.getNome() + "',"
					+ "'" + pessoa.getEndereco() + "');";
					

			smt.executeUpdate(sql);
			con.close();


		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}


	public void editar(PessoaModel pessoa){
		try {
			
			logger.info("Editar informcao da potencia");
			
			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "update task set "
					+ " nome='"+ pessoa.getNome() + "',"
					+ " endereco='" + pessoa.getEndereco() + "',"
					+ " telefone='"+ pessoa.getTelefone() + ";";

			smt.executeUpdate(sql);
			con.close();

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}


	public void update(PessoaModel pessoa){
		try {

			logger.info("Atualizando informacao da potencia");
			
			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "update task set "
					+ "nome='"+ pessoa.getNome() + "', "
					+ "endereco='" + pessoa.getEndereco() + "', "
					+ "telefone='"+ pessoa.getTelefone() + ";";
					
			smt.executeUpdate(sql);
			con.close();

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}*/


	public List<Potencia> listar(){
		try{
			logger.info("Lista de todas as potencia");
			
			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "select * from potencia";
			ResultSet rs = smt.executeQuery(sql);
			List<Potencia> list = new ArrayList<>();
			while( rs.next() ) {
				Potencia potencia = new Potencia();
				potencia.setId(rs.getInt(1));
				potencia.setNome(rs.getString(2));
				list.add(potencia);
			}
			con.close();
			return list;

		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		return Collections.emptyList();
	}

/*
	public void excluir(int id){
		try{
			logger.info("Excluindo potencia");
			
			con  = new ConexaoBancoDado().connectionPostgreSQL();
			smt = con.createStatement();
			String sql = "DELETE FROM pessoa WHERE id=" + id + ";";
			smt.executeUpdate(sql);
			con.close();
		} catch ( Exception e ) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
*/
}
