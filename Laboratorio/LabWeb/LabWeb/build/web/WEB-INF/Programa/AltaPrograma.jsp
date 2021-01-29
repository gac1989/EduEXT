<%-- 
    Document   : AltaPrograma
    Created on : 09/10/2020, 04:50:00 PM
    Author     : arena
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Programa</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){ 
                $("#nombrep").on("keyup", function(){ 
                    var nombrep = $("#nombrep").val();
                    $.ajax({
                        type:'POST',
                        data:{nombrep: nombrep},
                        url:'checkPrograma',
                        success:function(result){
                            console.log(result);
                            var res = result.result1;
                            if(res=="true"){
                                $('#res').css("display", "inline");
                                $('#res').html("Ya existe el programa");
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
        <script>
            function validacion(){
                var nombrep = document.getElementById('nombrep').value;
                var descripcion = document.getElementById('descripcion').value;
                var fechaini = document.getElementById('fechaini').value;
                var fechafin = document.getElementById('fechafin').value;
                if(!nombrep || !descripcion || !fechaini ||  !fechafin ){
                    alert("INGRESE TODOS LOS DATOS");
                    return false;
                } 
                else{
                    return true;
                }
            }
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
                            <form action="Programa" method="post" onsubmit="return validacion()">
                                <div class="form-group">
                                    <label >Escriba el nombre del Programa:</label>
                                    <input type="text" class="form-control" name="nombrep" placeholder="Nombre" id="nombrep">
                                </div>
                                <div class="form-group">
                                    <span style="color:red" id="res"></span>
                                </div>
                                <div class="form-group">
                                    <label >Escriba su descripción:</label>
                                    <input type="text" class="form-control" name="descripcion" placeholder="Descripcion" id="descripcion">
                                </div>
                                <div class="form-group">
                                    <label >Inicio del programa:</label>
                                    <input type="date" class="form-control" name="fechaini" id="fechaini">
                                </div>
                                <div class="form-group">
                                    <label >Fin del programa:</label>
                                    <input type="date" class="form-control" name="fechafin" id="fechafin">
                                </div>
                                <div class="form-group">
                                    <label >Seleccione su imagen:</label>
                                    <input type="file" name="txtDireccion" value="" size="50"/>
                                </div>
                                
                                <div class="form-group">
                                    <label>Cursos:</label><br>               
                                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <select id="cur" name="cur" multiple>
                                        <c:forEach items="${cursos}" var="cur" varStatus="loop">
                                            <option>
                                                ${cur}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                
                                <button type="submit" class="btn btn-primary">Enviar</button>
                                <a href="Home" id="cancel" name="cancel" class="btn btn-default">Cancel</a> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
    </body>
</html>
