/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datatypes.DTPrograma;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author arena
 
 */
@Entity
public class Programa implements Serializable {
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
	private String nombre;
	private String descripcion;
        @Temporal(TemporalType.DATE)
	private Date fecha_ini;
        @Temporal(TemporalType.DATE)
	private Date fecha_fin;
        @Temporal(TemporalType.DATE)
	private Date fecha_alta;
        //@ElementCollection
        @ManyToMany(mappedBy = "programas")
	@MapKey(name = "nombre") 
        private Map<String,Curso> Cursos;
	@JoinTable(name = "programaInscripcionP")
        private List <InscripcionP>inscripciones;
        private String imagenDir;

    public Programa() {
    }
	
	//Metodos
	
    public Programa(String nombre, String descripcion, Date fecha_ini, Date fecha_fin, Date fecha_alta){
	    this.nombre=nombre;
	    this.descripcion=descripcion;
	    this.fecha_ini=fecha_ini;
	    this.fecha_fin=fecha_fin;
	    this.fecha_alta=fecha_alta;
            this.Cursos = new HashMap();
	    this.inscripciones=new LinkedList();
            this.Cursos = new HashMap<>();
    }
    
    public Programa(String nombre, String descripcion, Date fecha_ini, Date fecha_fin, Date fecha_alta, String imagen){
	    this.nombre=nombre;
	    this.descripcion=descripcion;
	    this.fecha_ini=fecha_ini;
	    this.fecha_fin=fecha_fin;
	    this.fecha_alta=fecha_alta;
            this.Cursos = new HashMap();
	    this.inscripciones=new LinkedList();
            this.Cursos = new HashMap<>();
            this.imagenDir = imagen;
                  
    }
    
    public DTPrograma getDatos(){
    
        return new DTPrograma(this.nombre,this.descripcion,this.fecha_ini,this.fecha_fin,this.fecha_alta, this.imagenDir);
            

    }
    
    public String getimagenDir(){
        return this.imagenDir;
    }
    
    public void setimagenDir(String imagenDir){
        this.imagenDir = imagenDir;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
       
    
    public void addInscripcion (InscripcionP inscp){
        inscripciones.add(inscp);
    }
    
    
    public Date getFecha_Alta(){
        return fecha_alta;
    }
    
    public void setFechaAlta(Date fecha_alta){
        this.fecha_alta = fecha_alta;
    }

    public Map<String, Curso> getCursos() {
        return Cursos;
    }

    public List getInscripciones() {
        return inscripciones;
    }
    
    public Curso obtenerCurso(String nombre){
        return Cursos.get(nombre);
    }
    
    public void agregarCurso(Curso c){
        if(this.obtenerCurso(c.getNombre()) == null){
            Cursos.put(c.getNombre(), c);
            System.out.println("se agrego el curso: " + c.getNombre() + "al programa: " + this.nombre);
        }
    }
    
}
