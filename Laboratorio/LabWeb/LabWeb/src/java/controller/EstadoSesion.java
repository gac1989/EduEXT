/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *
 * @author Igui
 */
public enum EstadoSesion {
    NO_LOGIN,           // nunca intentó iniciar sesión
    LOGIN_ESTUDIANTE,
    LOGIN_DOCENTE,// tiene la sesión iniciada
    LOGIN_INCORRECTO    // le erro a la sesión al menos una vez
}
