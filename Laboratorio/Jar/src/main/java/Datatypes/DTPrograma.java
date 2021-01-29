package Datatypes;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPrograma {
	
	//Atributos
	
	private String nombre;
	private String descripcion;
	private Date fecha_ini;
	private Date fecha_fin;
	private Date fecha_alta;
        private String imagenDir;

    public String getImagenDir() {
        return imagenDir;
    }
	
	//Metodos

        public DTPrograma(String nombre, String descripcion, Date fecha_ini, Date fecha_fin,Date fecha_alta){
            this.nombre=nombre;
            this.descripcion=descripcion;
            this.fecha_ini=fecha_ini;
            this.fecha_fin=fecha_fin;
            this.fecha_alta=fecha_alta;
        }
        
        public DTPrograma(String nombre, String descripcion, Date fecha_ini, Date fecha_fin,Date fecha_alta, String imagenDir){
            this.nombre=nombre;
            this.descripcion=descripcion;
            this.fecha_ini=fecha_ini;
            this.fecha_fin=fecha_fin;
            this.fecha_alta=fecha_alta;
            this.imagenDir = imagenDir;
        }
        
        
	public String getNombre() {
		return this.nombre;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public Date getFechaInicial() {
		return this.fecha_ini;
	}
	public Date getFechaFinal() {
		return this.fecha_fin;
	}
	public Date getFechaAlta() {
		return this.fecha_alta;
	}
}
	
	