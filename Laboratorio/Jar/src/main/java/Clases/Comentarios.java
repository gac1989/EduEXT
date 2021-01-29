/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datatypes.DTComentarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author Surface
 */
@Entity
public class Comentarios implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String comentario;
    private Estudiante est;
    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="respuestasComentarios")
    private List<Comentarios> respuestas;
    //no estaria mal agregarle fecha

    public List<Comentarios> getRespuestas() {
        return respuestas;
    }
    
    public void agregarRespuesta(Comentarios resp){
        respuestas.add(resp);
    }

    public Comentarios() {
    }

    public long getId() {
        return id;
    }
    
    public Comentarios(String comentario, Estudiante est) {
        this.comentario = comentario;
        this.est = est;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }
    
    public DTComentarios getDatos(){
        List respuestas = new ArrayList();
        if(respuestas!=null){
            for(Comentarios res : this.respuestas){
                respuestas.add(res.getDatos());
            }
        }
        return new DTComentarios(id, comentario, est.getDatos(),respuestas);
    }
    
    public List<DTComentarios> getDTRespuestas(){
        List respuestas = new ArrayList();
        for(Comentarios res : this.respuestas){
            respuestas.add(res.getDatos());
        }
        return respuestas;
    }
    
    
}
