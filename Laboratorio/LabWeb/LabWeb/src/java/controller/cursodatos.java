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
import servidor.DtInstituto;

/**
 *
 * @author admin
 */
public class cursodatos extends HttpServlet {

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
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        String curso = request.getParameter("cur");
        try{
            DtCurso dtcur = port.obtenerCurso(curso);
            List<String> ed = port.edicionesCurso(curso);
            List<String> prog = port.programasCurso(curso);
            request.setAttribute("curso", dtcur);
            request.setAttribute("ediciones", ed);
            request.setAttribute("programas", prog);
            request.getRequestDispatcher("/WEB-INF/Curso/CursoDatos.jsp").forward(request, response);
        }catch(Exception e){
            request.getRequestDispatcher("/Error.html").forward(request, response);
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
   
        String curso = request.getParameter("cur");
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        DtCurso dtcur = port.obtenerCurso(curso);
        List<String> ediciones = port.edicionesCurso(curso);
        List<String> programas = port.programasCursos(curso);
        DtInstituto tryinst = port.obtenerInstitutoCurso(curso);
        String inst = null;
        if(tryinst!=null){
            inst = tryinst.getNombre();
            System.out.println("INSTITUTO="+ inst);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        String result1 = "";
        String result2 = "";
        String result3 = "<img src=\"" + dtcur.getImagenDir() + "\" >";
        
         for(Object edi : ediciones){
            result1 = result1 + "<label><a href=\"consultaediciondecurso?inst="+inst+"&cur="+ curso + "&ed=" + edi + "\">" + edi +"</a></label><br>";
         
        }
         
        for(Object prog : programas){
            result2 = result2 + "<label><a href=\"consultaPrograma?prog="+ prog + "\">"+ prog +"</a></label><br>";
         
        }
        
        if(curso!=null){     
            port.visitasCurso(curso);
        }
        
        float calif = port.obtenerCalifCurso(curso);
        String calificacion = Float.toString(calif);
        JSONObject j = new JSONObject(); 
        j.put("nombre", dtcur.getNombre());
        j.put("descripcion", dtcur.getDescripcion());
        j.put("duracion", dtcur.getDuracion());
        j.put("horas", dtcur.getCantHoras());
        j.put("creditos", dtcur.getCantCred());
        j.put("fechaReg", dtcur.getFechaReg().toString());
        j.put("url", dtcur.getUrl());
        j.put("calificacion", calificacion);
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
