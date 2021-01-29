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
import servidor.DtInscripcionE;
import servidor.EstadoEdicion;
import servidor.EstadoInscripcion;

/**
 *
 * @author admin
 */
public class IngresarResultado extends HttpServlet {

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
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        int aceptados = 0;
        if(nick2!=null && port.esDocente(nick2)){
            List<String> institutos = port.listarInstitutos();
            String cur = request.getParameter("cur");
            String esfin = request.getParameter("final");
            String edi = request.getParameter("edi");
            boolean notas=true;
            List<DtInscripcionE> inscriptos = null;
            if(cur!=null && edi!=null){
                inscriptos = port.listarDatosInscEdicion(cur, edi);
                for(DtInscripcionE insc : inscriptos){
                    String nick = insc.getEst().getNick();
                    String nota = request.getParameter(nick);
                    if(insc.getEstado()==EstadoInscripcion.ACEPTADA){
                        aceptados++;
                    }
                    if(nota != null && !nota.equals("")){
                        port.cambiarNota(cur, edi, nick, Integer.parseInt(nota));
                    }
                    else{
                        if(insc.getNota()==0 && insc.getEstado()==EstadoInscripcion.ACEPTADA){
                            notas=false;
                        }
                    }
                }
                System.out.println("LA CANTIDAD DE INSCRIPTOS ES: " + inscriptos.size() + " LA CANTIDAD DE ACEPTADOS ES: " + aceptados);
                if((esfin!=null && esfin.equals("final") && notas)||(esfin!=null && esfin.equals("final") && aceptados==0)){
                    port.cambiarEstadoEdicion(cur, edi, EstadoEdicion.CERRADA);
                }
            }
            request.setAttribute("institutos", institutos);
            request.getRequestDispatcher("/WEB-INF/Edicion/IngresarResultado.jsp").forward(request, response);
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
"                    console.log(\"ENTREE\");\n" +
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
        String result1 ="<tr> <th>Nombre</th><th>Fecha insc</th><th>Estado</th><th>Video</th><th>Nota</th></tr>";
        String nombre;
        DtInscripcionE datosins;
        for(Object insc : inscriptos){
            datosins = (DtInscripcionE) insc;
            if(datosins.getEstado()==EstadoInscripcion.ACEPTADA){
                String nick = datosins.getEst().getNick();
                nombre = datosins.getEst().getNick();
                result1 = result1 + "<tr><td name=\"est\" id=\"est\">&nbsp&nbsp" + nombre + "</td>" 
                + "<td name=\"fech\" id=\"fech\">" + datosins.getFechaInsc().toString() + "</td>" +
                "<td name=\"estado\" id=\"estado\">" + datosins.getEstado().toString() + "</td>" +
                "<td><iframe width=\"200\" height=\"150\" src=\"" +datosins.getVideo() + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></td>\n<td> <input type=\"number\" min=\"1\" max=\"12\" class=\"form-control\" name=\"" + nick + "\" placeholder=\"Nota\" id=\""+ nick +"\">\n</td>" +
                "</tr>";
            }
        }
        j.put("result1",result1);
        j.put("chanchada",chanchada);
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
