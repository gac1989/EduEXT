/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servidor.ParseException_Exception;
/**
 *
 * @author admin
 */
public class Edicion extends HttpServlet {

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
        /* TODO output your page here. You may use following sample code. */
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(nick2!=null && port.esDocente(nick2)){
            request.getRequestDispatcher("/WEB-INF/Edicion/Edicion.jsp").forward(request, response);
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
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        List<String> institutos = port.listarInstitutos();
        request.setAttribute("institutos", institutos); 
        String nombreEd = request.getParameter("nombreEd");
        String fechaIni = request.getParameter("fechaIni");
        String fechaFin = request.getParameter("fechaFin");
        String fechaPub = request.getParameter("fechaPub");
        String llevaCupos = request.getParameter("Radio");
        String nombreCurso = request.getParameter("cur");
        int cupos = 0;
        String imagenNom = request.getParameter("txtDireccion");
        String imagenDir = "images/logo.png";
        
        if(imagenNom!=null && !imagenNom.equals("") ){
                imagenDir = "images/"+imagenNom;
        }
        if(llevaCupos!=null && llevaCupos.equals("Si")){
            cupos = Integer.parseInt(request.getParameter("cuposEd"));
        }
        else{ 
            cupos = 10000;
        }
        String[] doc = request.getParameterValues("docentes");
        List lista = new ArrayList();
        if(doc!=null){
            lista = Arrays.asList(doc);
        }
        if(nombreEd!=null && !port.checkEdicion(nombreCurso, nombreEd)){
            try {
                port.altaEdicionCurso(nombreCurso, nombreEd, fechaIni, fechaFin, cupos, fechaPub, imagenDir, lista);
            } catch (ParseException_Exception ex) {
                Logger.getLogger(Edicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        String inst = request.getParameter("inst");
        List<String> cursos = port.cursosInstituto(inst);
        //ArrayList<String> cursos = ICU.cursosInstituto(inst);
        List<String> docentes = port.listarDocentesInstituto(inst);
        //ArrayList<String> docentes = ICU.listarDocentesInstituo(inst);
        //request.setAttribute("cursos", cursos);  
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject(); 
        String result1 = "";
        String result2 = ""; 
        for(Object curso : cursos){
            result1 = result1 + "<option>" + curso + "</option> ";
        }
        for(Object docente : docentes){
            result2 = result2 + "<option>" + docente + "</option>";
        }
        j.put("result1",result1);
        j.put("result2",result2);
        response.getWriter().write(j.toString());
        //out.print(j.toJSONString());
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
