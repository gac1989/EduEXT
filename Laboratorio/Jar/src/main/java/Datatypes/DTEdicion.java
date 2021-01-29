package Datatypes;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTEdicion {

	//Atributos
	
    private String nombre;
    private Date fecha_ini;
    private Date fecha_fin;
    private int cupo_max;
    private Date fecha_pub;
    private Map docentes;
    private List inscripciones;
    private String imagenDir;
    private EstadoEdicion estado;
    private int cupo_fijo;

    public int getCupo_fijo() {
        return cupo_fijo;
    }

    public void setCupo_fijo(int cupo_fijo) {
        this.cupo_fijo = cupo_fijo;
    }
    
    public EstadoEdicion getEstado() {
        return estado;
    }

    public String getImagenDir() {
        return imagenDir;
    }

    //Metodos
	
    public DTEdicion(String nombre,Date fecha_ini,Date fecha_fin,int cupo_max,Date fecha_pub, Map docentes, List inscripciones){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;
        this.docentes=docentes;
        this.inscripciones=inscripciones;
    }
  
    public DTEdicion(String nombre,Date fecha_ini,Date fecha_fin,int cupo_max,Date fecha_pub){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;

    }
    
    public DTEdicion(String nombre,Date fecha_ini,Date fecha_fin,int cupo_max,Date fecha_pub, EstadoEdicion estado){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;
        this.estado=estado;
    }
    
    public DTEdicion(String nombre,Date fecha_ini,Date fecha_fin,int cupo_max,Date fecha_pub, String imagenDir){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;
        this.imagenDir = imagenDir;

    }   
        
    
     public DTEdicion(String nombre,Date fecha_ini,Date fecha_fin,int cupo_max,Date fecha_pub, String imagenDir, EstadoEdicion estado, int cupo_fijo){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;
        this.imagenDir = imagenDir;
        this.estado=estado;
        this.cupo_fijo=cupo_fijo;
    }   
     
	public String getNombre() {
		return this.nombre;
	}
	public Date getFechaIni() {
		return this.fecha_ini;
	}
	public Date getFechaFin() {
		return this.fecha_fin;
	}
	public int getCuposMax() {
		return this.cupo_max;
	}
	public Date getFechaPub() {
		return this.fecha_pub;
	}
	public Map getDocentes(){
            return this.docentes;
    }
    public List getInscripciones(){
        return this.inscripciones;
    }
}
