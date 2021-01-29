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
import servidor.DtCurso;

/**
 *
 * @author Surface
 */
public class valorarCursoEst extends HttpServlet {

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
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        List<DtCurso> cursos = port.cursosEstudiante(nick);
        request.setAttribute("cursos", cursos);
        String nombreC = request.getParameter("cur");
        String nota = request.getParameter("nota");
        //!nombreC.equals("") && !nick.equals("") && !nota.equals("")
        if(nombreC != null && nota != null && !nota.equals("") && !nombreC.equals("") && !port.checkValoracionEst(nombreC, nick)){
            int calif = Integer.parseInt(nota);
            port.calificarCurso(nombreC, nick, calif);
        }
        String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
        if(nick2!=null && !port.chekusuario(nick2) && !port.esDocente(nick2)){
            request.getRequestDispatcher("/WEB-INF/Curso/valorarCurso.jsp").forward(request, response);
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
        String nick = (String) request.getSession().getAttribute("usuario_logueado");
        response.setContentType("application/json");
        String nombreC = request.getParameter("cur");
        float nota = port.obtenerCalifCurso(nombreC);
        String not = Float.toString(nota);
        
        JSONObject j = new JSONObject(); 
        j.put("result1", not);
        j.put("result2", port.checkValoracionEst(nombreC, nick));
        j.put("result3", port.notaEstCurso(nombreC, nick));
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
