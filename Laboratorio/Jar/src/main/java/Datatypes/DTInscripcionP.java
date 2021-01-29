/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datatypes;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTInscripcionP {
    private DTUsuario est;
    private EstadoPrograma estadoPrograma;
    private Date fecha_insc;
    private Date fecha_aprob;

    public DTInscripcionP(DTUsuario u1, EstadoPrograma estado, Date fecha_ini, Date fecha_aprob){
        this.est=u1;
        this.estadoPrograma=estado;
        this.fecha_insc=fecha_insc;
        this.fecha_aprob=fecha_aprob;
    }
    
    public DTUsuario getEst() {
        return est;
    }
    
  
    
    public EstadoPrograma getEstadoPrograma() {
        return estadoPrograma;
    }

    public Date getFecha_insc() {
        return fecha_insc;
    }

    public Date getFecha_aprob() {
        return fecha_aprob;
    }
}
