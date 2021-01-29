<%-- 
    Document   : ConsultaEdiCurso
    Created on : 17/10/2020, 03:16:10 PM
    Author     : german
--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <jsp:include page="/WEB-INF/Scripts/cargarCursos.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarEdiciones.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarCursosCat.jsp"/>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#inst').on("click", function(){
                    cargarCursos();
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#cur").on("change",function(){
                    cargarEdiciones();
                })
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                    $('#edi').on("click", function(){
                        cargarDatos();
                    });
                }
            );
        </script>
        <script>
            function cargarDatos(){
                var edi = $('#edi').val();
                var cur = $('#cur').val();
                console.log("La edicion seleccionada es: " + edi);
                $.ajax({
                    type:'POST',
                    data:{edi: edi, cur: cur},
                    url:'ediciondatos',
                    success:function(result){
                        console.log(result);
                        $('#nombre').html(result.nombre);
                        $('#fechaini').html(result.fechaini);
                        $('#fechafin').html(result.fechafin);
                        $('#cuposmax').html(result.cuposmax);
                        $('#cuposfijo').html(result.cuposfijos);
                        $('#fechapub').html(result.fechapub);
                        $('#docentes').html(result.result1);
                        $('#inscripciones').html(result.inscripciones);
                        $('#img').html(result.result2);
                        $('#estado').html(result.estado);
                    }
                });
            }
        </script>
        <%
            String inst = (String)request.getAttribute("inst");
            String cur = (String)request.getAttribute("cur");
            String ed = (String)request.getAttribute("edi");
            System.out.println("Instituto: " + inst + " Curso "+ cur + " Edicion: " + ed);
            if(inst!=null && cur != null && ed != null){
        %>
        <script>
            $(document).ready(function(){
                var inst = document.getElementById("inst");
                inst.value="<%=inst%>";
                cargarCursos();
                var cur = document.getElementById("cur");
                cur.value="<%=cur%>";
                cargarEdiciones();
                var ed = document.getElementById("edi");
                ed.value="<%=ed%>";
                cargarDatos();
            });
        </script>
        <%};%> 
        <script type="text/javascript">
            $(document).ready(function(){
                $('#cat').on("click", cargarCursosCat);  
            });
        </script>
        <title>ConsultaEdicionCurso</title>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/MOBILE-INF/panel_m_estudiante.jsp"/>
        </div>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">       
                <h1 class="titulo"> Consulta de Edición de Curso</h1>  
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
                            <div class="form-group">  
                                <label>Edición:</label><br>
                                <select id="edi" name="edi">
                                </select>
                            </div>
                            <jsp:include page="/WEB-INF/Edicion/ediciondatos.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
