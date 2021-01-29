<%-- 
    Document   : consultacurso
    Created on : oct 16, 2020, 4:19:29 p.m.
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de curso</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <jsp:include page="/WEB-INF/Scripts/Pestana.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarCursos.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarDatos.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarCursosCat.jsp"/>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#inst").on("click",cargarCursos);
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#cur").on("change", cargarDatos);
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
              $('#cat').on("click", cargarCursosCat);  
            });
        </script>
        <%
            String cur = (String)request.getAttribute("curso");
            String inst = (String)request.getAttribute("instituto");
            if(cur != null && inst != null){
        %>
        <script>
            $(document).ready(function(){
                var inst = document.getElementById("inst");
                inst.value="<%=inst%>";
                cargarCursos();
                var cur = document.getElementById("cur");
                cur.value ="<%=cur%>";
                cargarDatos();
            });
        </script>
        <%};%>
        
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <h1 class="titulo">Consulta de curso</h1>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <div class="form-group">  
                                <label>Buscar por Instituto:</label><br>
                                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                <select id="inst" name="inst" >
                                    <c:forEach items="${institutos}" var="inst" varStatus="loop">
                                        <option>
                                            ${inst}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">  
                                <label>Buscar por Categoria:</label><br>
                                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                <select id="cat" name="cat" >
                                    <c:forEach items="${categorias}" var="cat" varStatus="loop">
                                       <option>
                                            ${cat}
                                       </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">  
                                <label>Curso</label><br>
                                <select id="cur" name="cur">
                                     
                                </select>
                            </div>
                            <div class="tab">
                                <button class="tablinks" onclick="pestana(event, 'general')" id="defaultOpen" name="defaultOpen">General</button>
                                <button class="tablinks" onclick="pestana(event, 'ediciones')">Ediciones</button>
                                <button class="tablinks" onclick="pestana(event, 'programas')">Programas</button>
                            </div>
                            <div id="general" class="tabcontent">
                                <div class="form-group">
                                    <label>Nombre:</label> <h7 name="nombre" id="nombre"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Calificacion:</label> <h7 name="calif" id="calif"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Descripción:</label><h7 name="descripcion" id="descripcion"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Duración:</label> <h7 name="duracion" id="duracion"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Horas:</label>  <h7 name="horas" id="horas"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Créditos:</label>  <h7 name="creditos" id="creditos"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Fecha Registro:</label>  <h7 name="fechaReg" id="fechaReg"></h7>
                                </div>
                                <div class="form-group" name="url" id="urldiv">
                                    <label>URL:</label>  <h7 name="url" id="url"></h7>
                                </div> 
                                <div class="form-group" name="result" id="result">
                                    <h3 >Imagen: </h3>   
                                    <img>
                                </div>
                            </div>
                            <div id="ediciones" class="tabcontent">
                                <div id="edi" name="edi" class="form-group">
                                </div>
                            </div>
                            <div id="programas" class="tabcontent">
                                <div id="prog" name="prog" >
                                    <a href="programadatos?prog="></a><br>
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
