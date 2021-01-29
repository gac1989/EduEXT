/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datatypes;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Surface
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTComentarios {
    
    private Long id;
    private String comentario;
    private DTUsuario datosEst;
    private List<DTComentarios> respuestas;

    public DTComentarios(Long id, String comentario, DTUsuario datosEst, List<DTComentarios> respuestas) {
        this.comentario = comentario;
        this.datosEst = datosEst;
        this.id = id;
        this.respuestas=respuestas;
    }

    public List getRespuestas() {
        return respuestas;
    }

    public String getComentario() {
        return comentario;
    }

    public DTUsuario getDatosEst() {
        return datosEst;
    }

    public Long getId() {
        return id;
    }
   
}
