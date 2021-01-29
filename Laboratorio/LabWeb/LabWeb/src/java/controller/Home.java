/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.EstadoSesion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
  
    public static void initSession(HttpServletRequest request) {
            HttpSession session = request.getSession();
            if (session.getAttribute("paginas_navegadas") == null) {
                    session.setAttribute("paginas_navegadas", 0);
            }
            if (session.getAttribute("estado_sesion") == null) {
                    session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
            }
    }
    
    public static EstadoSesion getEstado(HttpServletRequest request){
		return (EstadoSesion)request.getSession().getAttribute("estado_sesion");
    }
    
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        initSession(req);
        String mobile = (String)req.getSession().getAttribute("mobile");
        switch(getEstado(req)){
            case NO_LOGIN:
                    // hace que se ejecute el jsp sin cambiar la url
                    if(mobile!=null && mobile.equals("true")){
                        System.out.println("El menu es mobileee");
                        req.getRequestDispatcher("/index_mobile.jsp").forward(req, resp);
                        return;
                    }
                    else{
                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                    }
                    break;
            case LOGIN_INCORRECTO:
                    // hace que se ejecute el jsp sin cambiar la url
                    req.getRequestDispatcher("/Error.jsp").forward(req, resp);
                    break;
            case LOGIN_ESTUDIANTE:
                    if(mobile!=null && mobile.equals("true")){
                        req.getRequestDispatcher("/index_mobile_e.jsp").forward(req, resp);
                        return;
                    }
                    // manda una redirección a otra URL (cambia la URL)
                    req.getRequestDispatcher("/index_estudiante.jsp").forward(req, resp);
                    break;
            case LOGIN_DOCENTE:
                    // manda una redirección a otra URL (cambia la URL)
                    req.getRequestDispatcher("/index_docente.jsp").forward(req, resp);
                    break;
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
