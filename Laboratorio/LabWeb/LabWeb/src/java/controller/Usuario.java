/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 * @author admin
 */
public class Usuario extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            servidor.PublicadorService service = new servidor.PublicadorService();
            servidor.Publicador port = service.getPublicadorPort();
            List<String> institutos = port.listarInstitutos();
            request.setAttribute("institutos", institutos); 
            String nick = request.getParameter("nick");
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String fecha = request.getParameter("fecha");
            String correo = request.getParameter("correo");
            String tipo = request.getParameter("Radio");
            String pass = request.getParameter("password");
            String pass2 = request.getParameter("password2");
            String[] inst = request.getParameterValues("inst");
            String imagenNom = request.getParameter("txtDireccion");
            String imagenDir = "images/logo.png";
            if(imagenNom!=null && !imagenNom.equals("")){
                imagenDir = "images/"+imagenNom;
            }
            
            List lista = new ArrayList();
            if(inst!=null){
                lista = Arrays.asList(inst);
            }
        
            Boolean ckpass = false;
            if(pass != null && pass2 != null && pass.equals(pass2)){
                ckpass=true;
            }
            if(nombre!=null && ckpass){
                //servidor.DtUsuario datos = new servidor.DtUsuario(nick, nombre, apellido, correo, fechaDate,pass, imagenDir);
                if(tipo.equals("Estudiante")){ 
                   port.altausuario(nick, nombre, apellido, correo, fecha, pass, false, imagenDir, lista);
                }
                else{ 
                    port.altausuario(nick, nombre, apellido, correo, fecha, pass, true, imagenDir, lista);
                }
            }
            String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
            if(nick2==null){
                request.getRequestDispatcher("/WEB-INF/Usuario/Usuario.jsp").forward(request, response); 
            }
            else{
                response.sendRedirect("Error.jsp");
            }
        }catch(Exception e){
            System.out.print("No funciono");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
