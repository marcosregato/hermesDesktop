/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import org.apache.log4j.Logger;

/**
 *
 * @author marcos
 */
public class ConfigController {
    
     static Logger logger = Logger.getLogger(ConfigController.class);
    
    public void configurarSistema(){
        try {
            
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
        }
    }
    
    public void enviarLogSistema(boolean enviar){
         try {
            
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
        }
    }
    
}
