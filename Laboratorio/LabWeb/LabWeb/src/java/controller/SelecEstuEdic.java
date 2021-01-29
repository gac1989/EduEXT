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
import servidor.DtCurso;
import servidor.DtEdicion;
import servidor.DtInscripcionE;
import servidor.EstadoEdicion;

/**
 *
 * @author Surface
 */
public class SelecEstuEdic extends HttpServlet {

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
         try{
            servidor.PublicadorService service = new servidor.PublicadorService();
            servidor.Publicador port = service.getPublicadorPort();
            String[] elegidos = request.getParameterValues("elegidos");
            String[] rechazados = request.getParameterValues("rechazados");
            String cur = request.getParameter("cur");
            String edi = request.getParameter("edi");
            DtEdicion e1=null;
            if(edi!=null && cur != null){
                e1 = port.datosEdiciones(cur, edi);
            }
            if(e1!=null && elegidos.length<=e1.getCupoFijo()){
                System.out.println("ME METI A INSCRIBIR");
                if(elegidos!=null){
                   for(String nombre : elegidos){
                       if(elegidos.length>=1){
                           port.modificarEstadoInscripcion(cur, edi, nombre, servidor.EstadoInscripcion.ACEPTADA);
                       }
                   }
                }
                if(rechazados!=null){
                   for(String nombrer : rechazados){
                       port.modificarEstadoInscripcion(cur, edi, nombrer,servidor.EstadoInscripcion.RECHAZADA);
                   }
               }
               if(elegidos!=null && elegidos.length>=1){
                   port.cambiarEstadoEdicion(cur, edi, EstadoEdicion.ABIERTA);
               }
            }
            String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
            if(nick2!=null && port.esDocente(nick2)){
                request.getRequestDispatcher("/WEB-INF/Edicion/SelecEstuEdic.jsp").forward(request, response);
            }
            else{
                response.sendRedirect("Error.jsp");
            }
         }catch(Exception e){
             System.out.println("No funciono");
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
        List<String> institutos = port.listarInstitutos();
        request.setAttribute("institutos", institutos);
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
        List<DtInscripcionE> inscriptos = port.listarDatosInscEdicion(cur, edi);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject();
        String chanchada = "<script>\n" +
"            $(document).ready(function(){\n" +
"                $('input[type=\"checkbox\"]').on('click', function() {\n" +
"                    var $box = $(this);\n" +
"                       if ($box.is(\":checked\")) {\n" +
"                      // the name of the box is retrieved using the .attr() method\n" +
"                      // as it is assumed and expected to be immutable\n" +
"                      var group = \"input:checkbox[value='\" + $box.attr(\"value\") + \"']\";\n" +
"                      // the checked state of the group/box on the other hand will change\n" +
"                      // and the current value is retrieved using .prop() method\n" +
"                      $(group).prop(\"checked\", false);\n" +
"                      $box.prop(\"checked\", true);\n" +
"                    } else {\n" +
"                      $box.prop(\"checked\", false);\n" +
"                    }\n" +
"                });\n" +
"            });\n" +
"        </script>";
        String result1 ="<tr> <th>Nombre</th><th>Fecha insc</th><th>Estado</th><th>Video</th></tr>";
        //String result2 = "";
        //String result3 = "";
        String nombre;
        DtInscripcionE datosins;
        for(Object insc : inscriptos){
            datosins = (DtInscripcionE) insc;
            nombre = datosins.getEst().getNick();
            result1 = result1 + "<tr><td name=\"est\" id=\"est\"><input type=\"checkbox\" value=\"" + nombre + "\"name=\"rechazados\" onclick=\"checkAcept(\'rec\')\" >&#10060</input> <input type=\"checkbox\" value=\"" + nombre + "\"name=\"elegidos\" onclick=\"checkAcept(\'ace\')\">&#9989</input>&nbsp&nbsp" + nombre + "</td>" 
            + "<td name=\"fech\" id=\"fech\">" + datosins.getFechaInsc().toString() + "</td>" +
            "<td name=\"estado\" id=\"estado\">" + datosins.getEstado().toString() + "</td>" +
            "<td><iframe width=\"200\" height=\"150\" src=\"" +datosins.getVideo() + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></td>\n</tr>";
            
        }
        j.put("result1",result1);
        j.put("chanchada",chanchada);
       // j.put("result3",result3);
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
