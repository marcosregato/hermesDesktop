/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author marcos
 */
public class GerenteDeJanela {
    
    
    private static JDesktopPane jDesktopPane; 
    
    public GerenteDeJanela(JDesktopPane jDesktopPane){
        GerenteDeJanela.jDesktopPane = jDesktopPane;
    }
    
    public void abrirJanela(JInternalFrame jInternalFrame){
        if(jInternalFrame.isVisible()){
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
        }else{
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
        }
    }
    
}
