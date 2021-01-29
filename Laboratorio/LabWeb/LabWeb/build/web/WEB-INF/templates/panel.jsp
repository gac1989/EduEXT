<%-- 
    Document   : panel
    Created on : oct 14, 2020, 9:59:21 p.m.
    Author     : admin
--%>

<%@page import="controller.EstadoSesion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    
    <head>
    <style>
        h1 {
            text-align: center;
        }
        
        .titulo{
           text-decoration: underline;
        }
        
    </style>
    
    </head>
    
    <body>
       <% 
       EstadoSesion est = (EstadoSesion)request.getSession().getAttribute("estado_sesion");
       switch(est){
            case NO_LOGIN:
            %>    
                <jsp:include page="/WEB-INF/templates/panel_visitante.jsp"/>
            <%    break;
            
            case LOGIN_INCORRECTO:
            %>
            <jsp:include page="/WEB-INF/templates/panel_visitante.jsp"/>
            <%
            case LOGIN_ESTUDIANTE:
            %>   <jsp:include page="/WEB-INF/templates/panel_estudiante.jsp"/>     
            <% break;
            
            case LOGIN_DOCENTE:
                %>
                  <jsp:include page="/WEB-INF/templates/panel_docente.jsp"/>
              <% break;
        }
        %>
        
    </body>
    
    
</html>
 