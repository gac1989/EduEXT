/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servidor.DtCurso;
import servidor.DtPrograma;

/**
 *
 * @author admin
 */
public class Busqueda extends HttpServlet {

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
        String busqueda = request.getParameter("busqueda");
        List<DtCurso> cursos = port.datosCursos();
        List<DtPrograma> programas = port.datosProgramas();
        String resultado = ""; 
        Pattern pat = Pattern.compile(".*" + busqueda + ".*");
        if(busqueda.length()>=3){
            for(DtCurso c : cursos){
                Matcher mat1 = pat.matcher(c.getNombre());                                                                          
                Matcher mat2 = pat.matcher(c.getDescripcion()); 
                String inst = port.obtenerInstitutoCurso(c.getNombre()).getNombre();
                if(mat1.matches()|| mat2.matches()){
                    resultado = resultado + "<div class=\"panel panel-default\">" +
                   "<div class=\"panel-heading\">" +
                   "<h3 class=\"panel-title\"><a href=\"concultacurso?inst="+ inst+"&cur=" + c.getNombre()+"\"> Curso: " + c.getNombre() + "</a></h3></div>" +
                   "<div class=\"panel-body\">" + c.getDescripcion() +"</div></div><br>";
                }
            }
            for(DtPrograma p : programas){
                Matcher mat1 = pat.matcher(p.getNombre());                                                                          
                Matcher mat2 = pat.matcher(p.getDescripcion()); 

                if(mat1.matches()||mat2.matches()){
                    resultado = resultado + "<div class=\"panel panel-default\">" +
                   "<div class=\"panel-heading\">" +
                   "<h3 class=\"panel-title\"><a href=\"consultaPrograma?prog=" + p.getNombre()+"\"> Programa: " + p.getNombre() + "</a></h3></div>" +
                   "<div class=\"panel-body\">" + p.getDescripcion() +"</div></div><br>";
                }
            }
        }
        if(resultado.equals("")){
            resultado = resultado + "<h1>No se encontraron resultados</h1>";
        }   
        //resultado = resultado + "</div>";
        
        request.setAttribute("resultado", resultado);
        request.getRequestDispatcher("/WEB-INF/Busqueda/informacion.jsp").forward(request, response);
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
        processRequest(request, response);
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
