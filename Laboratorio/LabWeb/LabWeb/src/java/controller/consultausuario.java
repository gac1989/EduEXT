/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servidor.DtUsuario;

/**
 *
 * @author admin
 */
public class consultausuario extends HttpServlet {

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
        String usuario = request.getParameter("nick"); 
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
    
        if(usuario==null){
            List<DtUsuario> usuarios = port.consultaUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/WEB-INF/Usuario/listar.jsp").forward(request, response);
        }
        else{
            servidor.DtUsuario u1 = port.obtenerUsuario(usuario);
            List<servidor.DatosEdicion> e1 = null;
            List<String> p1 = null;
            if(usuario!=null){
                e1 = port.mostrarEdicionFULL(usuario);
                p1 = port.mostrarProgramasUsuario(usuario);
            }
            request.setAttribute("programas", p1);
            request.setAttribute("usuario", u1);
            request.setAttribute("ediciones", e1);
            String mobile = (String)request.getSession().getAttribute("mobile");
            if(mobile.equals("true")){
                request.getRequestDispatcher("/MOBILE-INF/perfil.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("/WEB-INF/Usuario/perfil.jsp").forward(request, response);
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
