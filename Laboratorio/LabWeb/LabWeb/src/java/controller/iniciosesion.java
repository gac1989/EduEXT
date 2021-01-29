/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servidor.DtUsuario;

/**
 *
 * @author admin
 */
public class iniciosesion extends HttpServlet {

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
            servidor.PublicadorService service = new servidor.PublicadorService();
            servidor.Publicador port = service.getPublicadorPort();
            HttpSession objSesion = request.getSession();
            EstadoSesion nuevoEstado;
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String mobile = (String)request.getSession().getAttribute("mobile");
            if(login==null || password==null || login.equals("")|| password.equals("") ){
                objSesion.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
                if(mobile!=null && mobile.equals("true")){
                    request.getRequestDispatcher("/MOBILE-INF/InicioSesionMobile.jsp").forward(request, response); 
                }
                else{
                    request.getRequestDispatcher("/WEB-INF/Usuario/InicioSesion.jsp").forward(request, response); 
                }
                return;
            }
            DtUsuario u1 = port.obtenerUsuario(login);
            if(u1!=null && u1.getContrasena().equals(password)){
                if(port.esDocente(u1.getNick())){
                    if(mobile.equals("false")){
                        nuevoEstado = EstadoSesion.LOGIN_DOCENTE;
                        request.getSession().setAttribute("usuario_logueado", u1.getNick());
                    }
                    else{
                        nuevoEstado = EstadoSesion.NO_LOGIN;
                    }
                }
                else{
                    nuevoEstado = EstadoSesion.LOGIN_ESTUDIANTE;
                    request.getSession().setAttribute("usuario_logueado", u1.getNick());
                }
            }
            else{
                
                u1 = port.obtenerUsuarioMail(login);
                
                if(u1!=null && u1.getContrasena().equals(password)){
                    
                    if(port.esDocente(u1.getNick())){
                        nuevoEstado = EstadoSesion.LOGIN_DOCENTE;
                    }
                    else{
                        nuevoEstado = EstadoSesion.LOGIN_ESTUDIANTE;
                    }
                    request.getSession().setAttribute("usuario_logueado", u1.getNick());
                }
                else{
                    nuevoEstado = EstadoSesion.NO_LOGIN;
                }

            }
            objSesion.setAttribute("estado_sesion", nuevoEstado);
            String user = (String) request.getSession().getAttribute("usuario_logueado");
            System.out.println("Usuario: " + user);
            request.setAttribute("user", user); 
            if(login != null &&(nuevoEstado == EstadoSesion.LOGIN_DOCENTE || nuevoEstado == EstadoSesion.LOGIN_ESTUDIANTE )){
                response.sendRedirect("Home");
            }
            else{
                System.out.println("ES MOBILEEE??" + mobile);
                if(mobile!=null && mobile.equals("true")){
                    request.getRequestDispatcher("/MOBILE-INF/InicioSesionMobile.jsp").forward(request, response); 
                }
                else{
                    request.getRequestDispatcher("/WEB-INF/Usuario/InicioSesion.jsp").forward(request, response); 
                }
            }
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
