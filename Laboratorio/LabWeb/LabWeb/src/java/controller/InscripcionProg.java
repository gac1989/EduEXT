/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Surface
 */
public class InscripcionProg extends HttpServlet {

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
            String programa = request.getParameter("prog");
            String nick = (String) request.getSession().getAttribute("usuario_logueado");
            if(programa!=null && nick!=null && !port.checkincripcionPrograma(programa,nick)){
             //   ICU.InscripcionPrograma(programa, nick, new Date());
                  port.inscripcionPrograma(programa, nick);
            }
            String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
            if(nick2!=null && !port.chekusuario(nick2) && !port.esDocente(nick2)){
                request.getRequestDispatcher("/WEB-INF/Programa/InscripcionProg.jsp").forward(request, response);
            }
            else{
                response.sendRedirect("Error.jsp");
            }
        }catch(Exception e){
            System.err.println("no funciono");
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
            List<String> programas = port.listarNombreProgramas();
            request.setAttribute("programas", programas);
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
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        String prog = request.getParameter("prog");
        String respuesta = "";
        boolean check = port.checkincripcionPrograma(prog, nick);
        if(!check){
            respuesta = "Puede inscribirse";
        }else{
            respuesta = "Ya esta inscripto";
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject();
        
        System.out.println("programa: " + prog + "usuario: " + nick + "y el check es: " + respuesta);
        
        j.put("mensaje", respuesta);                
        
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
