/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servidor.DtCurso;
import servidor.DtInstituto;

/**
 *
 * @author arena
 */
public class programadatos extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String prog = request.getParameter("prog");
        if(prog!=null) {
            servidor.DtPrograma programa = port.obtenerPrograma(prog);
             ArrayList<String> cursos = (ArrayList<String>) port.cursosPrograma(prog);
             request.setAttribute("cursos", cursos);
             request.setAttribute("programa", programa);
         }
         request.getRequestDispatcher("/WEB-INF/Programa/datosprograma.jsp").forward(request, response);
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
        String prog = request.getParameter("prog");
        servidor.DtPrograma dtprograma = port.obtenerPrograma(prog);
        List<String> catprog = port.categoriasProgramas(prog);
        List<DtCurso> cursos = port.dtCursosPrograma(prog);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject(); 
        String result1 = ""; 
        String result2 = "";
        String result3 = "<img src=\"" + dtprograma.getImagenDir() + "\" >";
        for(Object cp : catprog){
            result1 = result1 + "<option>"+ cp + "</option>";
         
        }
        for(DtCurso cur : cursos){
            String inst = port.obtenerInstitutoCurso(cur.getNombre()).getNombre();
            result2 = result2 + "<a href=\"concultacurso?inst="+ inst +"&cur="+ cur.getNombre() + "\">"+cur.getNombre()+"</a><br>";
        }
        j.put("result2",result2);
        j.put("result1",result1);
        j.put("nombre", dtprograma.getNombre());
        j.put("descripcion", dtprograma.getDescripcion());
        j.put("fechaini", dtprograma.getFechaIni().toString());
        j.put("fechafin", dtprograma.getFechaFin().toString());
        j.put("fechaalta", dtprograma.getFechaAlta().toString());
        j.put("foto", dtprograma.getImagenDir());
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
