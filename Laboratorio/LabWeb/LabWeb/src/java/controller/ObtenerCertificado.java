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
import servidor.DtInscripcionP;
import servidor.DtUsuario;
import servidor.EstadoEdicion;
import servidor.EstadoInscripcion;

/**
 *
 * @author Surface
 */
public class ObtenerCertificado extends HttpServlet {

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
        if(nick2!=null && !port.chekusuario(nick2) && !port.esDocente(nick2)){
            request.getRequestDispatcher("/WEB-INF/Programa/ObtenerCertificado.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("Error.jsp");
        }
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
            servidor.PublicadorService service = new servidor.PublicadorService();
            servidor.Publicador port = service.getPublicadorPort();
            String nick = (String) request.getSession().getAttribute("usuario_logueado");
            List<String> programas = port.mostrarProgramasUsuario(nick);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject();
        
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        String programa = request.getParameter("prog");
        port.actualizrEstadoPrograma(programa, nick);
        DtInscripcionP datosP =port.obtenerEstadoProg(programa, nick);
        String result1 = datosP.getEstadoPrograma().toString();
        
        String result2 = "<tr>\n" +
"                                                            <th>Curso</th>\n" +
"                                                            <th>Edicion</th>\n" +
"                                                            <th>Fecha Aprobado/Reprobado</th>\n" +
"                                                            <th>Estado</th>\n" +
"                                                            <th>Nota</th>\n" +
"                                                        </tr>";
        List<DtInscripcionE> inscripciones = port.datosInscripEstProg(programa, nick);
        DtUsuario u1 = null;
        if(inscripciones.get(0)!=null){
            u1 = inscripciones.get(0).getEst();
        }
        for(DtInscripcionE datos : inscripciones){
            if(datos.getEstado()==EstadoInscripcion.ACEPTADA){
                String nota = "";
                String edEst="";
                if(datos.getEstadoEdicion()==EstadoEdicion.ABIERTA){
                    edEst=" (SIN FINALIZAR)";
                }
                int valor = datos.getNota();
                String fecha = "";
                if(datos.getFechaAprobado()==null){
                    fecha="PENDIENTE";
                }
                else{
                    fecha=datos.getFechaAprobado().toString();
                }
                if(valor==0){
                    nota="PENDIENTE";
                }
                else{
                    nota = Integer.toString(valor);
                }
                result2 = result2 + "<tr>\n" +
"                       <td name=\"cur\" id=\"cur\">"+ datos.getNombreCurso()+"</td> \n" +
"                       <td name=\"edi\" id=\"edi\">"+ datos.getNombreEdicion()+"</td>\n" +
"                       <td name=\"fech\" id=\"fech\">"+fecha+"</td>\n" +
"                       <td name=\"estado\" id=\"estado\">"+datos.getEstadoCurso()+ edEst + "</td>\n" +
"                       <td name=\"nota\" id=\"nota\">"+nota+"</td>\n" +
"                  </tr>";
            }
        }
        String result3 = "  <div id=\"content\" style=\"width:800px; height:600px; padding:20px; text-align:center; border: 10px solid #787878; background-color: white;\" >\n" +
"                                    <div style=\"width:750px; height:550px; padding:20px; text-align:center; border: 5px solid #787878\">\n" +
"                                        \n" +
"                                    " +
"                                 <span style=\"font-size:50px; font-weight:bold\">Certificado de programa <b style=\"color:DarkTurquoise\">edEXT</b></span>\n" +
"                    <br><br>\n" +
"                    <span style=\"font-size:25px\"><i>Se certifica que el estudiante</i></span>\n" +
"                    <br><br>" + "<span style=\"font-size:30px\"><b>"+u1.getNombre() + " " + u1.getApellido() +"</b></span><br/><br/>\n" +
"                    <span style=\"font-size:25px\"><i>Ha completado el programa de formacion</i></span> <br/><br/>\n" +
"                    <span style=\"font-size:30px\">"+programa+" </span> <br/><br/>\n" +
"                    <span style=\"font-size:20px\">"+datosP.getFechaAprob()+"</span> <br/><br/><br/>\n";
        j.put("result1",result1);
        j.put("result2",result2);
        j.put("result3",result3);
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
