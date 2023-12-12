package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConexaoBancoDado {

	static Logger log = LogManager.getLogger(ConexaoBancoDado.class.getName());
	Connection c = null;
	Statement stmt = null;

	public boolean connectionSqlLite(String pathFileSQLITE, String pathCriarBanco) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(pathFileSQLITE);
			stmt.executeUpdate(pathCriarBanco);
			stmt.close();
			c.close();
			log.info("Opened database successfully");
			
			return true;
		} catch ( Exception e ) {
			log.info( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return false;
	}

	public Connection connectionPostgreSQL() {
		try {
			String USERNAME = "postgres";
			String PASSWORD = "postgres";
			String DATABASE_URL = "jdbc:postgresql://localhost:5432/hermesDB";

			Class.forName("org.postgresql.Driver"); 
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			return connection;
		} catch (Exception e) {
			log.info( e.getClass().getName() + ": " + e.getMessage() );
		}
		return null;
	}

	public static Connection connectionMySQL() {
		try {
			String USERNAME = "root";
			String PASSWORD = "admin123";
			String DATABASE_URL = "jdbc:mysql://localhost/sistemaComercialDB";

			Class.forName("org.mysql.jdbc.Driver"); 
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			return connection;
		} catch (Exception e) {
			log.info( e.getClass().getName() + ": " + e.getMessage() );
		}
		return null;
	}


}
