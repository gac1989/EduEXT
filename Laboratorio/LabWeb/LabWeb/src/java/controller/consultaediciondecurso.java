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

/**
 *
 * @author arena
 */
public class consultaediciondecurso extends HttpServlet {

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
        String inst = request.getParameter("inst");
        String cur = request.getParameter("cur");
        String ed = request.getParameter("ed");
        request.setAttribute("inst", inst);
        request.setAttribute("cur", cur);
        request.setAttribute("edi", ed);
        System.out.println("DATOS: Instituto: " + inst + " Curso "+ cur + " Edicion: " + ed);
        String mobile = (String)request.getSession().getAttribute("mobile");
        if(mobile.equals("true")){
            request.getRequestDispatcher("/MOBILE-INF/ConsultaEdiCurso_m.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/WEB-INF/Edicion/ConsultaEdiCurso.jsp").forward(request, response);
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
        //ArrayList<String> institutos = ICU.listarInstitutos();
        request.setAttribute("institutos", institutos);
        List<String> categorias = port.listarCategorias();
        //ArrayList<String> categorias = ICU.listarCategorias();
        request.setAttribute("categorias", categorias);
        
 //       String edi = request.getParameter("edi");
//        DTEdicion datosedicion = ICU.datosEdicion(curso, edi);
//        request.setAttribute("docentes", datosedicion.getDocentes());
        
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
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JSONObject j = new JSONObject(); 
        String result1 = "";
        for(Object curso : cursos){
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
