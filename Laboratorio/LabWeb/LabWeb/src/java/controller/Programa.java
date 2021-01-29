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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servidor.ParseException_Exception;

/**
 *
 * @author arena
 */
public class Programa extends HttpServlet {

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
            throws ServletException, IOException, ParseException, ParseException_Exception {
        response.setContentType("text/html;charset=UTF-8");
        servidor.PublicadorService service = new servidor.PublicadorService();
        servidor.Publicador port = service.getPublicadorPort();
        List cursos = port.mostrarCursos();
        ArrayList<String> cur = new ArrayList<String>();
        servidor.DtCurso dato; 
        for(Object c : cursos){
            dato = (servidor.DtCurso)c;
            cur.add(dato.getNombre());
        }
        request.setAttribute("cursos", cur); 
        String nombrep = request.getParameter("nombrep");
        String desc = request.getParameter("descripcion"); 
        String fechaDate = request.getParameter("fechaini");
        String fechaDate2 = request.getParameter("fechafin");
        String imagenNom = request.getParameter("txtDireccion");
        String imagenDir = "images/logo.png";
        String[] cursosProg = request.getParameterValues("cur");
        if(imagenNom!=null && !imagenNom.equals("") ){
                imagenDir = "images/"+imagenNom;
        }
        if (nombrep != null && port.obtenerPrograma(nombrep) == null){
              port.crearPrograma(nombrep,desc,fechaDate,fechaDate2,imagenDir);
        }
        else{
            if(nombrep != null){
                port.modificarDatosPrograma(nombrep,desc,fechaDate,fechaDate2,imagenDir);
            }
        } 
        if(nombrep!=null && cursosProg!=null){
            for(String curso : cursosProg){
                port.agregarCursoPrograma(nombrep, curso);
            }
        }
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(nick2!=null && port.esDocente(nick2)){
            request.getRequestDispatcher("/WEB-INF/Programa/AltaPrograma.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
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
