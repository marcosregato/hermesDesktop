package business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import importaDado.CriarTableSQL;

public class ConfigurSistema {
	
	Logger log = LogManager.getLogger(ConfigurSistema.class.getName());
	
	/**
	 * @param confirmar
	 * importar as informações do arquivo EXCEL
	 * */
	public void isImportDados(boolean confirmar) {//, String pathExcel) {
		
		try {
			if(confirmar == true) {
				CriarTableSQL criar = new CriarTableSQL();
			}
		} catch (Exception e) {
			log.info("Erro => "+ e.getMessage());
		}
	}
	
	/**
	 * Escolher qual o banco sera usado no sistema
	 * @param banco
	 */
	public void tipoBancoDados(String banco) {
		try {
			
		} catch (Exception e) {
			log.info("Erro => "+ e.getMessage());
		}
	}

}
