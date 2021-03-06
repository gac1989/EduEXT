<%-- 
    Document   : SelecEstuEdic
    Created on : Oct 21, 2020, 11:18:32 AM
    Author     : Surface
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarCursos.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarEdicionesDocente.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Seleccionar Estudiante para una Edicion</title>
        <style>
            table, td{
                border: 1px solid black;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#inst').on("click", cargarCursos);  
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#cur').on("click", function(){
                    var cur = $('#cur').val();
                    if(cur=="Seleccione un curso"){
                        return;
                    }
                    console.log("El curso seleccionado es: " + cur);
                    $.ajax({
                        async: false,
                        type:'GET',
                        data:{cur: cur},
                        url:'edicionesAbiertas',
                        success:function(result){
                            console.log(result);
                            $('#edi').html(result.result1);
                        }
                    });
                });  
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#edi').on("click", function(){
                    var edi = $('#edi').val();
                    var cur = $('#cur').val();
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
                            $('#fechapub').html(result.fechapub);
                        }
                    });
                });  
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#edi').on("click", function(){
                    var edi = $('#edi').val();
                    var cur = $('#cur').val();
                    $.ajax({
                        type:'POST',
                        data:{edi: edi, cur: cur},
                        url:'IngresarResultado',
                        success:function(result){
                            console.log(result);
                            $('#filas').html(result.result1);
                            $('#chanchada').html(result.chanchada);
                        }
                    });
                });  
            });
        </script>
        <script>
            function setFinal(){
                var hidden = document.getElementById("final");
                hidden.value="final";
            }
        </script>
        
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <div id="chanchada"></div>
        <h1 class="titulo">Ingresar resultado de una edicion de curso</h1>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">      
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">
                            <form action="IngresarResultado" method="get" onsubmit="return validacion()">
                                <div class="form-group">
                                    <label>Institutos:</label><br>               
                                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <select id="inst" name="inst">
                                        <c:forEach items="${institutos}" var="inst" varStatus="loop">
                                            <option>
                                                ${inst}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">  
                                    <label>Curso:</label><br>
                                    <select id="cur" name="cur">
                                    </select>
                                </div>
                                <div class="form-group">  
                                    <label>Edicion:</label><br>
                                    <select id="edi" name="edi">
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Nombre:</label> <h7 name="nombre" id="nombre"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Fecha inicio:</label> <h7 name="fechaini" id="fechaini"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Fecha fin:</label> <h7 name="fechafin" id="fechafin"></h7>
                                </div>
                                <div class="form-group">
                                    <label>Fecha de publicación:</label> <h7 name="fechapub" id="fechapub"></h7>
                                </div>
                                <div>
                                    <table name="filas" id ="filas" class="table table-bordered">
                                    </table>    
                                </div>    
                                <input type="submit" class="btn btn-danger" value="Aceptar y finalizar" onclick="return setFinal()"></input>
                                <input type="hidden" value="no" id="final" name="final">
                                <input type="submit" class="btn btn-primary" value="Aceptar"></input>
                                <a href="Home"><input type="button" class="btn btn-default" value="Cancelar"></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
