/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servidor.DatosEdicion;
import servidor.DtEdicion;
import servidor.EstadoEdicion;

/**
 *
 * @author admin
 */
public class edicionesAbiertas extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet edicionesAbiertas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet edicionesAbiertas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String curso = request.getParameter("cur");
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(curso!=null && nick2!=null && port.esDocente(nick2)){
            List<DatosEdicion> ediciones = port.mostrarEdicionFULL(nick2);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            JSONObject j = new JSONObject(); 
            String result1 = "";
            for(DatosEdicion ed : ediciones){
                System.out.println("El estado de edicion es: " + ed.getEstado().toString());
                if(ed.getEstado()==EstadoEdicion.ABIERTA && ed.getCurso().equals(curso)){
                    result1 = result1 + "<option>" + ed.getEdicion() + "</option>";
                }
            }
            j.put("result1",result1);
            response.getWriter().write(j.toString());
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
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String curso = request.getParameter("cur");
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(curso!=null && nick2!=null && port.esDocente(nick2)){
            List<DatosEdicion> ediciones = port.mostrarEdicionFULL(nick2);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            JSONObject j = new JSONObject(); 
            String result1 = "";
            for(DatosEdicion ed : ediciones){
                System.out.println("El estado de edicion es: " + ed.getEstado().toString());
                if(ed.getEstado()==EstadoEdicion.INSCRIBIENDO && ed.getCurso().equals(curso)){
                    result1 = result1 + "<option>" + ed.getEdicion() + "</option>";
                }
            }
            j.put("result1",result1);
            response.getWriter().write(j.toString());
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
