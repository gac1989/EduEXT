/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datatypes.DTUsuario;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Bruno
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nick;
    private String nombre;
    private String apellido;
    private String correo;
    @Temporal(TemporalType.DATE)
    private Date fecha_de_nac;
    private String contrasena;
    private String imagenDir;
    
    public Usuario() {
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    //Metodos
    
    public Usuario(String nick, String nombre, String apellido, String correo, Date fecha_de_nac, String contrasena){
        this.nick=nick;
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.fecha_de_nac=fecha_de_nac;
        this.contrasena = contrasena;
    }
    
    public Usuario(String nick, String nombre, String apellido, String correo, Date fecha_de_nac, String contrasena, String imagenDir){
        this.nick=nick;
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.fecha_de_nac=fecha_de_nac;
        this.contrasena = contrasena;
        this.imagenDir = imagenDir;
    }
  
    public DTUsuario getDatos(){
        return new DTUsuario(this.nick, this.nombre, this.apellido, this.correo, this.fecha_de_nac, this.contrasena);
    }
    
    
    public void modificarDatos(DTUsuario datos){
        this.nombre=datos.getNombre();
        this.apellido=datos.getApellido();
        this.fecha_de_nac=datos.getFecha();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha_de_nac() {
        return fecha_de_nac;
    }

    public void setFecha_de_nac(Date fecha_de_nac) {
        this.fecha_de_nac = fecha_de_nac;
    }
    
    public String getImagenDir(){
        return this.imagenDir;
    }
    
    
}
