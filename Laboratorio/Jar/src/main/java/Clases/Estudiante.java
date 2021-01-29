/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
@Entity
public class Estudiante extends Usuario implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue (strategy = GenerationType.AUTO)
//    private Long id;

    public Estudiante() {
    }
//Metodos
    public Estudiante(String nick, String nombre,String apellido, String correo, Date fecha_de_nac, String contrasena ){
        super(nick, nombre, apellido, correo, fecha_de_nac, contrasena);
    }
    
    public Estudiante(String nick, String nombre,String apellido, String correo, Date fecha_de_nac, String contrasena, String imagenDir){
        super(nick, nombre, apellido, correo, fecha_de_nac, contrasena, imagenDir);
    }
    
    
}
