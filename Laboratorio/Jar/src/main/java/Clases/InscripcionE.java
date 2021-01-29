/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datatypes.DTInscripcionE;
import Datatypes.EstadoCurso;
import Datatypes.EstadoEdicion;
import Datatypes.EstadoInscripcion;
import java.util.Date;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Bruno
 */
//HACE REFERENCiA AL TIPO ASOCIATIVO ESTUDIANTE/EDICION
@Entity
@Table (name = "inscripcionEstu")
public class InscripcionE implements Serializable{
    
    //Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    Date fecha_insc;
    @Temporal(TemporalType.DATE)
    Date fecha_aprobado;
    @OneToOne
    Estudiante est;
    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estado;
    private String video;
    private int nota;
    @Enumerated(EnumType.STRING)
    private EstadoCurso estadoCurso;
    
    public DTInscripcionE getDatos(){
        return new DTInscripcionE(fecha_insc, est.getDatos(), estado, video, nota, estadoCurso, fecha_aprobado);
    }

    public EstadoCurso getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(EstadoCurso estadoCurso) {
        this.estadoCurso = estadoCurso;
    }
    
    public void setNota(int nota) {
        this.nota = nota;
        if(nota>=6){
            this.estadoCurso = EstadoCurso.APROBADO;
        }else{
            this.estadoCurso = EstadoCurso.REPROBADO;
        }
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
    public InscripcionE() {
    }
    
    //Metodos
    
    public InscripcionE(Date fecha_insc, Estudiante est, String video){
        this.fecha_insc=fecha_insc;
        this.est=est;
        this.estado=EstadoInscripcion.PENDIENTE;
        this.video=video;
        this.nota=0;
        this.estadoCurso = EstadoCurso.CURSANDO;
        this.fecha_aprobado=null;
    }
    
    public EstadoInscripcion getEstado(){
        return this.estado;
    }
    
    public void setEstado(EstadoInscripcion estado){
        this.estado=estado;
    }
    
    public void modificarDatos(Date fecha_nueva){
        this.fecha_insc=fecha_nueva;
    }

    public Estudiante getEst() {
        return est;
    }

    public Date getFecha() {
        return fecha_insc;
    }

    public Date getFecha_aprobado() {
        return fecha_aprobado;
    }

    public void setFecha_aprobado(Date fecha_aprobado) {
        this.fecha_aprobado = fecha_aprobado;
    }
    
    
    
}
