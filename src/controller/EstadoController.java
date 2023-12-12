package controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import dao.EstadoDao;
import model.Estado;

public class EstadoController{
	
	static Logger logger = Logger.getLogger(EstadoController.class);
	
	private EstadoDao dao = new EstadoDao();
	
	public List<Estado> salvar(){
		try {
			return dao.listar();
		} catch (Exception e) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
		}
		return Collections.emptyList();
	}
}
