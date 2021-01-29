/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servidor.ParseException_Exception;

/**
 *
 * @author admin
 */
public class modificardatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, ParseException_Exception {
        response.setContentType("text/html;charset=UTF-8");
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        servidor.DtUsuario u1 = port.obtenerUsuario(nick);
        request.setAttribute("datos", u1);
        request.setAttribute("user", nick);
        String nombre = (String)request.getParameter("nomUsr");
        String apellido = (String)request.getParameter("apUsr");
        String fechaNac = (String)request.getParameter("fechaNac");
       
        System.out.println("El nombre usuario a modificar es: " + nick);
        if(nick!=null){
            port.modificarDatosUsuario(nick, nombre, apellido, fechaNac); 
        }
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(nick2!=null && !port.chekusuario(nick2)){
            request.getRequestDispatcher("/WEB-INF/Usuario/modificardatos.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("Error.jsp");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(modificardatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(modificardatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(modificardatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(modificardatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
