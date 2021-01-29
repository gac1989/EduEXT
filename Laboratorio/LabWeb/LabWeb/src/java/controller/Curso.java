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
import servidor.DtCurso;
import servidor.ParseException_Exception;

/**
 *
 * @author admin
 */
public class Curso extends HttpServlet {

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
        String nomCur=request.getParameter("nomCur");
        String desCur=request.getParameter("desCur");
        String durCur=request.getParameter("durCur");
        String horasCur=request.getParameter("horasCur");
        String credCur=request.getParameter("credCur");
        String urlCur=request.getParameter("url");
        String inst = request.getParameter("inst");
        String imagenNom = request.getParameter("txtDireccion");
        String imagenDir = "images/logo.png";
        if(imagenNom!=null && !imagenNom.equals("") ){
                imagenDir = "images/"+imagenNom;
        }
        String[] previas = request.getParameterValues("previas");
        String[] cat = request.getParameterValues("cat");
        List lista = new ArrayList();
        if(previas!=null){
            lista = Arrays.asList(previas);
        }
        List lista2 = new ArrayList();
        if(cat!=null){
            lista2 = Arrays.asList(cat);
        }
        if(nomCur!=null && port.obtenerCurso(nomCur) == null){
            port.registrarCurso(inst, nomCur, desCur, durCur, Double.parseDouble(horasCur), Double.parseDouble(credCur), "", urlCur, imagenDir, lista, lista2);
        }else{
            if(nomCur != null){
            }
        } 
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(nick2!=null && port.esDocente(nick2)){
           request.getRequestDispatcher("/WEB-INF/Curso/Curso.jsp").forward(request, response);
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
        List<String> cat = port.listarCategorias();
        request.setAttribute("categorias", cat); 
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
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
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            String inst = request.getParameter("inst");
            servidor.PublicadorService service = new servidor.PublicadorService();
            servidor.Publicador port = service.getPublicadorPort();
            List<String> cur = port.cursosInstituto(inst);
            JSONObject j = new JSONObject(); 
            String result1 = "";
            for(Object curso : cur){
                result1 = result1 + "<option>" + curso + "</option> ";

            }
            j.put("result1",result1);
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
