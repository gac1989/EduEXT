<%-- 
    Document   : consultacurso
    Created on : oct 16, 2020, 4:19:29 p.m.
    Author     : admin
--%>
<%@page import="servidor.DtPrograma"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Consulta de curso</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <jsp:include page="/WEB-INF/Scripts/Pestana.jsp"/>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <%
            DtPrograma prog = (DtPrograma)request.getAttribute("programa");
            if(prog!=null){
        %>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">  
                <h1 class="titulo">Consulta de Programa</h1>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                <%};%>
                        <%  
                        String nombre="";
                        String descripcion="";
                        String fechaini="";
                        String fechafin="";
                        String fechaalta="";
                        if(prog!=null){
                            nombre=prog.getNombre();
                            descripcion=prog.getDescripcion();
                            fechaini=prog.getFechaIni().toString();
                            fechafin=prog.getFechaFin().toString();
                            fechaalta=prog.getFechaAlta().toString();
                        }
                    %>    
                            <div class="form-group">  
                            <% 
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            %>
                            </div>
                            <div class="tab">
                                <button class="tablinks" onclick="pestana(event, 'general')" id="defaultOpen" name="defaultOpen">General</button>
                                <button class="tablinks" onclick="pestana(event, 'cursos')">Cursos</button>
                            </div>
                            <div id="general" class="tabcontent">
                                <h3 class="titulo">Nombre:</h3><h3 id="nombre">&nbsp;&nbsp; <%= nombre %></h3>
                                <h3 class="titulo">Descripcion:</h3><h3 id="descripcion"> &nbsp;&nbsp;<%= descripcion%></h3>
                                <h3 class="titulo">Fecha inicial:</h3><h3 id="fechaini">&nbsp;&nbsp; <%= fechaini%></h3>
                                <h3 class="titulo">Fecha final:</h3><h3 id="fechafin">&nbsp;&nbsp; <%= fechafin %></h3>
                                <h3 class="titulo">Fecha de alta:</h3><h3 id="fechaalta">&nbsp;&nbsp; <%= fechaalta %></h3>
                            </div>
                            <div id="cursos" class="tabcontent">
                                <div id="cur" name="cur">
                                    <ul class="list-group">
                                    <%
                                            ArrayList<String> cursos=(ArrayList)request.getAttribute("cursos");
                                            if(cursos!=null){
                                                for(String c : cursos){
                                                    System.out.println(c);
                                        %>
                                        <li class="list-group-item"><h2><a href="cursodatos?cur=<%=c%>"><%=c%></a></h2></li><br>
                                        <%
                                                }
                                            }
                                    %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/Scripts/scriptPestana.jsp"/>
    </body>
</html>
