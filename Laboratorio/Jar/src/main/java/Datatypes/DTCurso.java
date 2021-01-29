package Datatypes;


import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCurso {
	
	//Atributos
	
	private String nombre;
	private String descripcion;
	private String duracion;
	private double cant_horas;
	private double cant_cred;
	private Date fecha_reg;
	private String url;
        private String imagenDir;
        private int visitas;
        
        
	//Metodos
	
	public DTCurso(String nombre,String descripcion, String duracion,double cant_horas,double cant_cred, Date fecha_reg, String url) {
            this.nombre=nombre;
            this.descripcion=descripcion;
            this.duracion=duracion;
            this.cant_horas=cant_horas;
            this.cant_cred=cant_cred;
            this.fecha_reg=fecha_reg;
            this.url=url;
            
	}
        
        public DTCurso(String nombre,String descripcion, String duracion,double cant_horas,double cant_cred, Date fecha_reg, String url, String imagenDir, int visitas) {
            this.nombre=nombre;
            this.descripcion=descripcion;
            this.duracion=duracion;
            this.cant_horas=cant_horas;
            this.cant_cred=cant_cred;
            this.fecha_reg=fecha_reg;
            this.url=url;
            this.imagenDir = imagenDir;
            this.visitas = visitas;
            
	}

    public int getVisitas() {
        return visitas;
    }
        
        

    public String getImagenDir() {
        return imagenDir;
    }
	
	public String getNombre(){
		return this.nombre;
	}
	public String getDescripcion(){
		return this.descripcion;
	}
	public String getDuracion(){
		return this.duracion;
	}
	public double getHoras(){
		return this.cant_horas;
	}
	public double getCreditos(){
		return this.cant_cred;
	}
	public Date getFechaReg(){
		return this.fecha_reg;
	}
	public String getUrl() {
		return this.url;
	}


  
}
