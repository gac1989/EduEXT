<%-- 
    Document   : AceptadosEdicion
    Created on : 21/10/2020, 11:11:33 PM
    Author     : german
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Listar aceptados edición</title>
        <jsp:include page="/WEB-INF/Scripts/cargarCursos.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarEdiciones.jsp"/>
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
                        type:'POST',
                        data:{cur: cur},
                        url:'listarAceptados',
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
                        url:'aceptadosedicion',
                        success:function(result){
                            console.log(result);
                            $('#filas').html(result.result1);
                        }
                    });
                });  
            });
        </script>
        <style type="text/css">
            h7 { color: dimgray }
        </style>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <h1 style="text-align:center" class="titulo">Listar aceptados a una Edición de Curso</h1>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">
                <div id="res">                  
                    <div class="container mt-5">
                        <div class="row">                    
                            <form action="aceptadosedicion" method="get">
                                <div class="form-group">
                                <p>Institutos:</p>               
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
                                    <p>Curso:</p>
                                    <select id="cur" name="cur">
                                    </select>
                                </div>
                                <div class="form-group">  
                                    <p>Edicion:</p>
                                    <select id="edi" name="edi">
                                    </select>
                                </div>
                                    <div class="form-group">
                                        <p>Nombre:</p> <h7 name="nombre" id="nombre"></h7>
                                    </div>
                                <div class="form-group">
                                    <p>Fecha inicio:</p> <h7 name="fechaini" id="fechaini"></h7>
                                </div>
                                <div class="form-group">
                                    <p>Fecha fin:</p> <h7 name="fechafin" id="fechafin"></h7>
                                </div>
                                <div class="form-group">
                                    <p>Cupos disponibles:</p> <h7 name="cuposmax" id="cuposmax"></h7>
                                </div>
                                <div class="form-group">
                                    <p>Fecha de publicación:</p> <h7 name="fechapub" id="fechapub"></h7>
                                </div>
                                <div>
                                    <table name="filas" id ="filas" class="table table-bordered">
                                    </table>    
                                </div>    
                                <br>
                                <br>
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
