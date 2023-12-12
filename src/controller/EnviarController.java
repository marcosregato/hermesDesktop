/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.EnviarMensagemBusiness;
import dao.EnviarDao;
import java.util.List;
import model.Pessoa;

/**
 *
 * @author marcos
 */
public class EnviarController {
    
    EnviarDao dao = new EnviarDao();
    
    public void getMensagem(List<String> listUsuario, boolean enviarTodos, String msg){
        
        if(enviarTodos == true){
            List<Pessoa> email = dao.enviarParaTodo();
            
        }else if(listUsuario !=null){
            EnviarMensagemBusiness enviar = new EnviarMensagemBusiness();
            enviar.enviar(listUsuario);
        }
        
    }
    
}
