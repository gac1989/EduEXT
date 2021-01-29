<%-- 
    Document   : ConsultaPrograma
    Created on : 20/10/2020, 03:18:10 PM
    Author     : arena
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <jsp:include page="/WEB-INF/Scripts/Pestana.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Consulta de Programa</title>
        <link rel="stylesheet" href="C:\Users\arena\OneDrive\Escritorio\LW\LW\LW\LW\style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script>
            function cargarDatos(){
                var prog = $('#prog').val();
                console.log("El programa seleccionado es: " + prog);
                $.ajax({
                    type:'POST',
                    data:{prog: prog},
                    url:'programadatos',
                    success:function(result){
                        console.log(result);
                        $('#nombre').html(result.nombre);
                        $('#descripcion').html(result.descripcion);
                        $('#fechaini').html(result.fechaini);
                        $('#fechafin').html(result.fechafin);
                        $('#fechaalta').html(result.fechaalta);
                        $('#cat').html(result.result1);
                        $('#cur').html(result.result2);    
                        $('#img').html(result.result3);
                    }
                });
            }
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#prog').on("click", cargarDatos);
            });
        </script>
        <%
        String prog = (String)request.getAttribute("programa");
        if(prog!=null){
        %>
         <script>
            $(document).ready(function(){
                var prog = document.getElementById("prog");
                prog.value="<%=prog%>";
                cargarDatos();
            });
        </script>
        <%};%>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">     
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>       
        <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <h1 class="titulo">Consulta de Programa</h1>        
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <div class="form-group">  
                                <label>Programas:</label><br>
                                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                <select id="prog" name="prog" >
                                    <c:forEach items="${programas}" var="prog" varStatus="loop">
                                        <option>
                                            ${prog}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">  
                            </div>
                            <div class="tab">
                                <button class="tablinks" onclick="pestana(event, 'general')" id="defaultOpen" name="defaultOpen">General</button>
                                <button class="tablinks" onclick="pestana(event, 'cursos')">Cursos</button>
                            </div>
                            <div id="general" class="tabcontent">
                                <h3 class="titulo" ><b>Nombre:</b></h3><h3 id="nombre">&nbsp;&nbsp;</h3>
                                <h3 class="titulo"><b>Descripcion:</b></h3><h3 id="descripcion"> &nbsp;&nbsp;</h3>
                                <h3 class="titulo"><b>Fecha inicial:</b></h3><h3 id="fechaini">&nbsp;&nbsp; </h3>
                                <h3 class="titulo"><b>Fecha final:</b></h3><h3 id="fechafin">&nbsp;&nbsp; </h3>
                                <h3 class="titulo"><b>Fecha de alta:</b></h3><h3 id="fechaalta">&nbsp;&nbsp; </h3>
                                <h3 class="titulo"><b>Imagen:</b></h3><h3>&nbsp;&nbsp;</h3>
                                <div id="img"></div>
                            </div>
                            <div id="cursos" class="tabcontent">
                                <div id="cur" name="cur">
                                    <ul class="list-group">
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
