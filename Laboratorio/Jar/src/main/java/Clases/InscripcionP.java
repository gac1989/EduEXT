package Clases;

import Datatypes.DTInscripcionP;
import Datatypes.EstadoPrograma;
import java.util.Date;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InscripcionP implements Serializable {
	
	//Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    Date fecha_insc;
    Estudiante est;
    @Temporal(TemporalType.DATE)
    private Date fecha_aprobado;
    @Enumerated(EnumType.STRING)
    private EstadoPrograma estado;

    public void setFecha_aprobado(Date fecha_aprobado) {
        this.fecha_aprobado = fecha_aprobado;
    }

    public Date getFecha_aprobado() {
        return fecha_aprobado;
    }
    
    public EstadoPrograma getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrograma estado) {
        this.estado = estado;
    }
    
    public InscripcionP() {
    }
    
    //Metodos
    
    public InscripcionP(Date fecha_insc, Estudiante est){
        this.fecha_insc=fecha_insc;
        this.est=est;
        this.estado = EstadoPrograma.CURSANDO;
    }
    
    public DTInscripcionP getDatos(){
        return new DTInscripcionP(this.est.getDatos(),this.estado,this.fecha_aprobado,this.fecha_insc);
    }
    
    public void modificarDatos(Date fecha_nueva){
        this.fecha_insc=fecha_nueva;
    }

    public Date getFecha_insc() {
        return fecha_insc;
    }

    public Estudiante getEst() {
        return est;
    }
}
