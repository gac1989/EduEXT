<%-- 
    Document   : index
    Created on : sep 22, 2020, 4:44:33 p.m.
    Author     : admin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"&amp;gt;>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script>
            $(document).ready(function(){
                $('#res').css("display", "none");
                $('#res3').css("display", "none");
                $('#inst').css("display", "none");
            });
        </script>
      
        <title>Regístrate</title>
        
        <script>
            function checkCorreo(){ 
                console.log("Estamos");
                var email = $("#correo").val();
                $.ajax({
                    type:'GET',
                    data:{email: email},
                    url:'checkusuario',
                    success:function(result){
                        console.log(result);
                        var res = result.result1;
                        if(res=="true"){
                            $('#res3').css("display", "block");
                            $('#res3').html("Este correo ya esta ingresado");
                            $('#boton').css("display", "none");
                            return true;
                        }
                        else{
                            $('#res3').html("");
                            if( $('#res').html()==""){
                                $('#boton').css("display", "inline");
                            }
                            return false;
                        }
                    }
                });
            };
            
        </script>
        
        <script>
            $(document).ready(function(){
                $("#correo").on("keyup", checkCorreo);
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){ 
                $("#nick").on("keyup", function(){ 
                    var nick = $("#nick").val();
                    $.ajax({
                        type:'POST',
                        data:{nick: nick},
                        url:'checkusuario',
                        success:function(result){
                            console.log(result);
                            var res = result.result1;
                            if(res=="true"){
                                $('#res').css("display", "inline");
                                $('#res').html("Ya existe el usuario");
                                $('#boton').css("display", "none");
                            }
                            else{
                                $('#res').html("");
                                if($('#res3').html()==""){
                                    $('#boton').css("display", "inline");
                                }
                            }
                        }
                    });
                });
            });
        </script>
        <script>
            function validacion(){
                var nick = document.getElementById('nick').value;
                var nombre = document.getElementById('nombre').value;
                var apellido = document.getElementById('apellido').value;
                var email = document.getElementById('correo').value;
                var password = document.getElementById('password2').value;
                var radios = document.getElementsByName('Radio');
                var check = false;
                for (var i = 0, length = radios.length; i < length; i++){
                    if (radios[i].checked){
                        check = true;
                        break;
                    }
                }
                var password2 = document.getElementById('password2').value;
                if(!nick || !nombre || !apellido || !email || !password || !password2){
                    alert("INGRESE TODOS LOS DATOS");
                    return false;
                } 
                else{
                    return true;
                }
            }
        </script>
        <script type="text/javascript">
            $(document).ready(function(){ 
                $("#password").on("keyup", function(){ 
                    var pass = $("#password").val();
                    var pass2 = $("#password2").val();
                    if(pass!=pass2){
                        $('#res2').html("Las contrasenias no coinciden");
                        $('#boton').css("display", "none");
                    }
                    else{
                        $('#res2').html("");
                        $('#boton').css("display", "inline");
                    }
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){ 
                $("#Docente").on("click", function(){ 
                   $('#inst').css("display", "inline");
                });
                $("#Estudiante").on("click", function(){ 
                   $('#inst').css("display", "none");
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){ 
                $("#password2").on("keyup", function(){ 
                    var pass = $("#password").val();
                    var pass2 = $("#password2").val();
                    if(pass!=pass2){
                        $('#res2').html("Las contrasenias no coinciden");
                        $('#boton').css("display", "none");
                    }
                    else{
                        $('#res2').html("");
                        $('#boton').css("display", "inline");
                    }
                });
            });
        </script>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">
                <h1 class="titulo">Ingrese los datos:</h1>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <form action="Usuario" method="post" onsubmit="return validacion()">
                                <div class="form-group">
                                    <label >Escriba su nick:</label>
                                    <input type="text" class="form-control" name="nick" placeholder="Nick" id="nick">
                                </div>
                                <div class="form-group">
                                    <span style="color:red" id="res"></span>
                                </div>
                                <div class="form-group">
                                    <label >Escriba su nombre:</label>
                                    <input type="text" class="form-control" name="nombre" placeholder="Nombre" id="nombre">
                                </div>
                                <div class="form-group">
                                    <label >Escriba su apellido:</label>
                                    <input type="text" class="form-control" name="apellido" placeholder="Apellido" id="apellido">
                                </div>
                                <div class="form-group">
                                    <label >Escriba su correo:</label>
                                    <input type="text" class="form-control" name="correo" id="correo" placeholder="Correo" id="correo">
                                </div>
                                <div class="form-group">
                                    <span style="color:red" id="res3"></span>
                                </div>
                                <div class="form-group">
                                    <label >Ingrese su fecha de nacimiento:</label>
                                    <input type="date" class="form-control" name="fecha" max="2002-12-21">
                                </div>
                                <div class="form-group">
                                    <label >Ingrese su contrasenia:</label>
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                                <div class="form-group">
                                    <span style="color:red" id="res2"></span>
                                </div>
                                <div class="form-group">
                                    <label >Repita su contrasenia:</label>
                                    <input type="password" class="form-control" name="password2" id="password2">
                                </div>
                                <div class="form-group">
                                    <input type="radio" name="Radio" value="Docente" id='Docente'>Docente<br>
                                    <input type="radio" name="Radio" value="Estudiante" id='Estudiante'>Estudiante<br>
                                </div>
                                <div class="form-group">
                                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <select id="inst" multiple name="inst">
                                        <c:forEach items="${institutos}" var="inst" varStatus="loop">
                                            <option>
                                                ${inst}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label >Seleccione su imagen:</label>
                                    <input type="file" name="txtDireccion" value="" size="50"/>
                                </div> 
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary"  id="boton">Enviar</button> &nbsp;
                                    <a href="Home" id="cancel" name="cancel" class="btn btn-default">Cancel</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
