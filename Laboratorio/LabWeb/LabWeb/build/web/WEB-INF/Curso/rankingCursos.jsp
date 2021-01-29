<%-- 
    Document   : rankingCursos
    Created on : 21/11/2020, 11:26:41 PM
    Author     : Surface
--%>

<%@page import="servidor.DtCurso"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
	<title>Cursos</title>
    </head>
    <body class="left-menu">  
        <div class="menu-wrapper">     
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">   
                <div class="container mt-5">
                    <div class="row">
                        <div class="form-group">
                               <h1>Ranking de cursos</h1>
                               <br>
                            <div class="col-sm">  
                                <% 
                                    List<DtCurso> cursos = (List)request.getAttribute("cursos");
                                    for(DtCurso curso: cursos){
                                %>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Curso: <%=curso.getNombre() %></h3>
                                    </div>
                                    <div class="panel-body">
                                        <h3 id="nombre">Visitas: <%=curso.getVisitas() %></h3>
                                    </div>
                                    <div class="panel-body">
                                        <img src="<%=curso.getImagenDir() %>">
                                    </div>
                                </div>
                                <%      } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
