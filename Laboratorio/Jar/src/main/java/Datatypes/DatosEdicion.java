/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosEdicion {
    private String Instituto;
    private String Curso;
    private String Edicion;
    private EstadoEdicion estado;

    public EstadoEdicion getEstado() {
        return this.estado;
    }

    public void setInstituto(String Instituto) {
        this.Instituto = Instituto;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

    public void setEdicion(String Edicion) {
        this.Edicion = Edicion;
    }

    public String getInstituto() {
        return Instituto;
    }

    public String getCurso() {
        return Curso;
    }

    public String getEdicion() {
        return Edicion;
    }

    public DatosEdicion(String Instituto, String Curso, String Edicion, EstadoEdicion estado) {
        this.Instituto = Instituto;
        this.Curso = Curso;
        this.Edicion = Edicion;
        this.estado=estado;
    }
}
