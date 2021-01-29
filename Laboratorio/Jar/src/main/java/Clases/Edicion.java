/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datatypes.DTEdicion;
import Datatypes.EstadoEdicion;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Bruno
 */
@Entity
public class Edicion implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fecha_ini;
    @Temporal(TemporalType.DATE)
    private Date fecha_fin;
    private int cupo_max;
    private int cupo_fijo;
    public void setCupo_fijo(int cupo_fijo) {
        this.cupo_fijo = cupo_fijo;
    }
    public int getCupo_fijo() {
        return cupo_fijo;
    }
    @Temporal(TemporalType.DATE)
    private Date fecha_pub;
    @Enumerated(EnumType.STRING)
    private EstadoEdicion estado;
    @JoinTable(name="comentariosEdicion")
    private List<Comentarios> comentarios;

    public List<Comentarios> getComentarios() {
        return comentarios;
    }
    
    public void agregarComentario(Comentarios coment){
        comentarios.add(coment);
    }

   public void setEstado(EstadoEdicion estado) {
        this.estado = estado;
        if(estado==EstadoEdicion.CERRADA){
            for(InscripcionE insc : inscripciones){
                insc.setFecha_aprobado(new Date());
            }
        }
    }
   
   public void setCupo_max(int cupo_max) {
        this.cupo_max = cupo_max;
    }
   
    public EstadoEdicion getEstado() {
        return estado;
    }
    //@ElementCollection
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name="edicionUsuario", joinColumns = @JoinColumn(name = "edicion_id"),
                inverseJoinColumns = @JoinColumn(name = "docentes_id") )
    @MapKey(name = "nick")  
    private Map<String, Docente> docentes;
    /*@JoinTable(name="inscripcione", joinColumns = @JoinColumn(name = "edicion_id"),
                inverseJoinColumns = @JoinColumn(name = "docentes_id") )*/
    @OneToMany//(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name="edicionInscripcionE")
    private List <InscripcionE>inscripciones;
    private String imagenDir;

    public Edicion() {
    }
    
    
    
    public Edicion(String nombre, Date fecha_ini, Date fecha_fin, int cupo_max, Date fecha_pub){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;
        this.cupo_fijo=cupo_max;
        docentes = new HashMap();
        inscripciones = new LinkedList();
        Date today = new Date();
        if (today.getYear() == fecha_ini.getYear() ){
            this.estado=EstadoEdicion.ABIERTA;
        }
        else{
            this.estado=EstadoEdicion.CERRADA;
        }
    }
    
    public Edicion(String nombre, Date fecha_ini, Date fecha_fin, int cupo_max, Date fecha_pub, String imagenDir){
        this.nombre=nombre;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.cupo_max=cupo_max;
        this.fecha_pub=fecha_pub;
        this.cupo_fijo=cupo_max;
        docentes = new HashMap();
        inscripciones = new LinkedList();
        this.imagenDir = imagenDir;
        Date today = new Date();
        if (today.getYear() == fecha_ini.getYear() ){
            this.estado=EstadoEdicion.INSCRIBIENDO;
        }
        else{
            this.estado=EstadoEdicion.CERRADA;
        }
    }
    
    public Edicion(String nombre){ //CONSTRUCTOR PARA PRUEBA
        this.nombre = nombre;
        this.cupo_max = 40;
      
    }
    
    public String getimagenDir(){
        return this.imagenDir;
    }
    
    public void setimagenDir(String imagenDir){
        this.imagenDir = imagenDir;
    }
    
    public DTEdicion getDatos(){
        return new DTEdicion(this.nombre,this.fecha_ini,this.fecha_fin,this.cupo_max,this.fecha_pub,this.imagenDir, this.estado, this.cupo_fijo);
    }
    public String getNombreEdicion(){
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
    
  
    public void agregarInscripcionE(InscripcionE ie){
        if(obtenerInscripcionE(ie.est.getNick()) == null){
            inscripciones.add(ie);
        }
    
    }
    
    public InscripcionE obtenerInscripcionE(String nombreE){
        InscripcionE ie;
        for(Object e : inscripciones){
          ie = (InscripcionE) e;
          if(ie.getEst().getNick().equals(nombreE)) return ie;
        }
        return null;
    }
    
    public Usuario obtenerDocente(String nick){
        return docentes.get(nick);
    }
    
    public void agregarDocente(Docente u){
        if(obtenerDocente(u.getNick())==null){
            docentes.put(u.getNick(), u);
        }
    }
    
    public void agregarInscripcion(InscripcionE insc){
        if(!inscripciones.contains(insc)){
            inscripciones.add(insc);
        }
    }
    
}


