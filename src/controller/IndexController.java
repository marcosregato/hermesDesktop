package controller;

import org.apache.log4j.Logger;

import dao.UsuarioDao;
import model.Usuario;

public class IndexController {
	
	static Logger logger = Logger.getLogger(IndexController.class);


    UsuarioDao dao = new UsuarioDao();
    
    public void acessarSistema(Usuario pessoa) {
    	try {
    		if(pessoa != null) {
        		//pessoa.setNome(txtLogin.getText());
        		//pessoa.setNome(txtSenha.getText());
        		dao.salvar(pessoa);
        		
        	}
		} catch (Exception e) {
			logger.info( e.getClass().getName() + ": " + e.getMessage());
		}
    }
}
