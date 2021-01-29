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
import servidor.DtComentarios;
import servidor.DtCurso;
import servidor.DtEdicion;

/**
 *
 * @author Surface
 */
public class ComEdicion extends HttpServlet {

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
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        if(nick!=null && !port.chekusuario(nick) && !port.esDocente(nick)){
            List<DatosEdicion> ediciones = port.mostrarEdicionFULL(nick);
            String nombreE = request.getParameter("edi");
            String comentario = request.getParameter("coment");
            if(nombreE != null && comentario != null && nick != null){
                port.comentarEdicion(nick, nombreE, comentario);
            }
            request.setAttribute("ediciones", ediciones);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/WEB-INF/Edicion/ComentarEdicion.jsp").forward(request, response);
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

    
    private String mostrarComentarios(List<DtComentarios> comentarios, int tab, String nombreE){
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        
        DtCurso datosCur = port.obtenerCursoDeEdicion(nombreE);
        
        
        if(comentarios.isEmpty()){
            return "";
        }
        String espacios = "";
        int cp = tab;
        while(cp>0){
            espacios = espacios + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            cp--;
        }
        String result = "";
        for(Object datos : comentarios){
            DtComentarios coment = (DtComentarios) datos;
            result = result + "<p>"+  espacios + coment.getDatosEst().getNick() + "(" + port.notaEstCurso(datosCur.getNombre(), coment.getDatosEst().getNick()) +  ") dice: " +coment.getComentario() + "</p><div id=\"campo"+coment.getId()+"\">"+espacios+"<input type=\"text\" id=\""+ coment.getId()+"\"><input type=\"button\" value=\"enviar\" onclick=enviarRespuesta(\""+coment.getId()+"\")></div>" + mostrarComentarios(coment.getRespuestas(), ++tab,nombreE);
            tab--;
        } 
        return result;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        response.setContentType("application/json");
        String nombreE = request.getParameter("edi");
        String result = "";
        JSONObject j = new JSONObject(); 
        result = mostrarComentarios(port.obtenerComentariosEdicion(nombreE),0,nombreE);
        j.put("result",result);
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
