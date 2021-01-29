/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 *
 * @author Surface
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCalifEstuCur {
    
  
    private int calif;
    private DTUsuario est;

    public DTCalifEstuCur(int calif, DTUsuario est) {
      
        this.calif = calif;
        this.est = est;
    }


    public int getCalif() {
        return calif;
    }

    public DTUsuario getEst() {
        return est;
    }

  
    

    
    
}
