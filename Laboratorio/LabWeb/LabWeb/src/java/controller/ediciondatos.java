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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servidor.DtEdicion;

/**
 *
 * @author arena
 */
public class ediciondatos extends HttpServlet {

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
       
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
        String cur = request.getParameter("cur");
        String ed = request.getParameter("ed");
        DtEdicion edicion = null;
        if(cur != null && ed != null){
            edicion = port.datosEdiciones(cur, ed);  
        }
        request.setAttribute("ed", edicion);
        List<String> docentes = port.listarDocentesEdicion(cur, ed);
        request.setAttribute("doc", docentes);
        request.getRequestDispatcher("/WEB-INF/Edicion/ediciondatos.jsp").forward(request, response);
        response.setContentType("text/html;charset=UTF-8");  
        
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
       
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String cur = request.getParameter("cur");
        String edi = request.getParameter("edi");
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        String respuesta = "";
        boolean check = port.existeRegistroInscripcionE(nick, cur, edi);
        if(!check){
            respuesta = "Puede inscribirse";
        }else{
            respuesta = "Ya esta inscripto";
        }
        DtEdicion dtedi = port.datosEdiciones(cur, edi);
        List<String> docentesE = port.listarDocentesEdicion(cur, edi);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject(); 
        String result1 = "";
        String result2 = "<img src=\"" + dtedi.getImagenDir() + "\" >";
        for(Object doc : docentesE){
            result1 = result1 + "<a href=\"consultausuario?nick="+doc+"\">"+ doc + "</a><br>";
        }
        j.put("nombre", dtedi.getNombre());
        j.put("fechaini", dtedi.getFechaIni().toString());
        j.put("fechafin", dtedi.getFechaFin().toString());
        j.put("cuposmax", dtedi.getCupoMax());
        j.put("fechapub", dtedi.getFechaPub().toString());
        j.put("result1",result1);
        j.put("cuposfijos",dtedi.getCupoFijo());
        j.put("mensaje", respuesta);
        j.put("estado", dtedi.getEstado().toString());
        j.put("result2",result2);
        response.getWriter().write(j.toString());
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
