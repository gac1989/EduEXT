<%-- 
    Document   : Curso
    Created on : oct 9, 2020, 12:04:22 a.m.
    Author     : admin
--%>
<%@page import="java.text.Format.Field"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Curso</title>
        <script type="text/javascript">
            $(document).ready(function(){ 
                $("#nomCur").on("keyup", function(){ 
                    var nomCur = $("#nomCur").val();
                    $.ajax({
                        type:'POST',
                        data:{nomCur: nomCur},
                        url:'checkCurso',
                        success:function(result){
                            console.log(result);
                            var res = result.result1;
                            if(res=="true"){
                                $('#res').css("display", "inline");
                                $('#res').html("Ya existe el curso");
                                $('#boton').css("display", "none");
                            }
                            else{
                                $('#res').html("");
                                $('#boton').css("display", "inline");
                            }
                        }
                    });
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#inst').on("click", function(){
                    var inst = $('#inst').val();
                    console.log("El instituto seleccionado es: " + inst);
                    $.ajax({
                        type:'POST',
                        data:{inst: inst},
                        url:'Edicion',
                        success:function(result){
                            console.log(result);
                            $('#previas').html(result.result1);
                        }
                    });
                });  
            });
        </script>
        <script>
            function validacion(){
                var nomCur = document.getElementById('nomCur').value;
                var desCur = document.getElementById('desCur').value;
                var durCur = document.getElementById('durCur').value;
                var horasCur = document.getElementById('horasCur').value;
                var credCur = document.getElementById('credCur').value;
                var url = document.getElementById('url').value;
                var e = document.getElementById("inst");
                if(!nomCur || !desCur || !durCur || horasCur<=0 || credCur<=0 || !url ){
                    alert("INGRESE TODOS LOS DATOS");
                    return false;
                } 
                else{
                    return true;
                }
            }
        </script>
        <style type="text/css">
            label { color: dimgray }
        </style>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <h1 style="text-align: center">Ingrese los datos:</h1>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <form action="Curso" method="get" onsubmit="return validacion()">          
                                <div class="form-group">
                                    <label>Escriba el nombre del curso:</label>
                                    <input type="text" class="form-control" name="nomCur" placeholder="Nombre" id="nomCur">
                                </div>
                                 <div class="form-group">
                                    <span style="color:red" id="res"></span>
                                </div>
                                 <div class="form-group">
                                    <label >Escriba la descripcion del curso:</label>
                                    <input type="text" class="form-control" name="desCur" placeholder="Descripcion" id="desCur">
                                </div>
                                <div class="form-group">
                                    <label >Ingrese la duracion del curso:</label>
                                    <input type="text" class="form-control" name="durCur" placeholder="Duracion" id="durCur">
                                </div>
                                <div class="form-group">
                                    <label >Ingrese la cantidad de horas del curso:</label>
                                    <input type="number" class="form-control" name="horasCur" placeholder="Horas" id="horasCur">
                                </div>
                                <div class="form-group">
                                    <label >Ingrese la cantidad de creditos del curso:</label>
                                    <input type="number" class="form-control" name="credCur" placeholder="Creditos" id="credCur">
                                </div>
                                
                                <div class="form-group">
                                    <label >Ingrese la URL del curso:</label>
                                    <input type="text" class="form-control" name="url" id="url">
                                </div>
                                <div class="form-group">
                                    <label>Institutos</label><br>
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
                                    <label>Previas</label><br>
                                    <select id="previas" multiple name="previas">
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Categorias</label><br>
                                    <select id="cat" multiple name="cat">
                                        <c:forEach items="${categorias}" var="cat" varStatus="loop">
                                            <option>
                                                ${cat}
                                            </option>
                                         </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label >Seleccione su imagen:</label>
                                    <input type="file" name="txtDireccion" value="" size="50"/>
                                </div>
                                <button type="submit" class="btn btn-primary" id="boton">Enviar</button>
                                <a href="Home" id="cancel" name="cancel" class="btn btn-default">Cancel</a> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
    </body>
</html>
