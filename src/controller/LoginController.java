/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LoginDao;
import model.Login;
import org.apache.log4j.Logger;

/**
 *
 * @author marcos
 */
public class LoginController {
    
    static Logger logger = Logger.getLogger(LoginDao.class);
    
    LoginDao dao = new LoginDao();
    public Login acessoSistema(String login, String senha){
        try {
            if((login != null) &&(senha!=null)){
                return (Login) dao.buscarLogin(login,senha);
            }
            
        } catch (Exception e) {
            logger.info( e.getClass().getName() + " : " + e.getMessage() );
        }
        
         return null;
    }
    
}
