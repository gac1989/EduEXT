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
public class DTInscripcionE {
    
    private Date fecha_insc;
    private Date fecha_aprobado;
    private DTUsuario est;
    private EstadoInscripcion estado;
    private String nombreEdicion;
    private String nombreCurso;
    private int nota;
    private EstadoEdicion estadoEdicion;
    private EstadoCurso estadoCurso;

    public DTInscripcionE(Date fecha_insc, DTUsuario datos, EstadoInscripcion estado, String video, int nota, EstadoCurso estadoCurso, Date fecha_aprobado) {
       this.fecha_insc=fecha_insc;
       this.est=datos;
       this.estado=estado;    
       this.video=video;
       this.nota=nota;
       this.estadoCurso=estadoCurso;
       this.fecha_aprobado=fecha_aprobado;
    }

    public EstadoCurso getEstadoCurso() {
        return estadoCurso;
    }

    public Date getFecha_aprobado() {
        return fecha_aprobado;
    }

    
    
    public EstadoEdicion getEstadoEdicion() {
        return estadoEdicion;
    }

  
    public int getNota() {
        return nota;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }
    private String video;
    
    public DTInscripcionE(Date fecha_insc, DTUsuario est,EstadoInscripcion estado, String video){
        this.fecha_insc = fecha_insc;
        this.est = est;
        this.estado = estado;
        this.video=video;
    }
    
     public DTInscripcionE(Date fecha_insc, DTUsuario est,EstadoInscripcion estado, String nomCurso, String nomEdi,String video){
        this.fecha_insc = fecha_insc;
        this.est = est;
        this.estado = estado;
        this.nombreEdicion = nomEdi;
        this.nombreCurso = nomCurso;
        this.video=video;
    }
    public DTInscripcionE(Date fecha_insc, DTUsuario est,EstadoInscripcion estado, String nomCurso, String nomEdi,String video, int nota, EstadoEdicion estadoEdicion){
        this.fecha_insc = fecha_insc;
        this.est = est;
        this.estado = estado;
        this.nombreEdicion = nomEdi;
        this.nombreCurso = nomCurso;
        this.video=video;
        this.nota=nota;
        this.estadoEdicion=estadoEdicion;
    }
    
    public DTInscripcionE(Date fecha_insc, DTUsuario est,EstadoInscripcion estado, String video, int nota, EstadoCurso estCurso){
        this.fecha_insc = fecha_insc;
        this.est = est;
        this.estado = estado;
        this.video=video;
        this.nota=nota;
        this.estadoCurso = estCurso;
    }
    
    public DTInscripcionE(Date fecha_insc, DTUsuario est,EstadoInscripcion estado, String nomCurso, String nomEdi,String video, int nota, EstadoEdicion estadoEdicion, EstadoCurso estadoCurso, Date fecha_aprob){
        this.fecha_insc = fecha_insc;
        this.est = est;
        this.estado = estado;
        this.nombreEdicion = nomEdi;
        this.nombreCurso = nomCurso;
        this.video=video;
        this.nota=nota;
        this.estadoEdicion=estadoEdicion;
        this.estadoCurso = estadoCurso;
        this.fecha_aprobado = fecha_aprob;
        
    }
    
    
    public Date getFecha(){
        return this.fecha_insc;
    }
    public DTUsuario getEstudiante(){
        return this.est;
    }
    public EstadoInscripcion getEstado(){
        return this.estado;
    }

    public String getNombreEdicion() {
        return nombreEdicion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }
    
    
    
}
