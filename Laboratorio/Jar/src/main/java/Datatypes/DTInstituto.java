package Datatypes;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTInstituto {
	
	//Atributos
	
	private String nombre;

	
	//Metodos
	
        public DTInstituto(String nombre){
            this.nombre=nombre;
     
        }
        
	public String getNombre() {
		return this.nombre;
	}
	
	
	
}
