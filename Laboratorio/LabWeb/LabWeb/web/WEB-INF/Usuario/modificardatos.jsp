<%-- 
    Document   : modificardatos
    Created on : oct 16, 2020, 3:26:04 p.m.
    Author     : admin
--%>
<%@page import="servidor.DtUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <!-- Basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Meta -->
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <!-- Site Meta -->
        <title>edEXT</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <script>
            function validacion(){
                var nick = document.getElementById('nick').value;
                var nombre = document.getElementById('nombre').value;
                var apellido = document.getElementById('apellido').value;
                var email = document.getElementById('correo').value;
                var password = document.getElementById('password2').value;
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar datos de usuario</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class="left-menu">    
        <div class="menu-wrapper">     
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>   
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <h1>Ingrese los datos a modificar en el usuario: <c:out value="${user}"/></h1>  
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <form action="modificardatos" method="post" onsubmit="return validacion()">          
                                <%DtUsuario u1 = (DtUsuario) request.getAttribute("datos");%>
                                <div class="form-group">
                                    <label >Escriba el nombre:</label>
                                    <input type="text" class="form-control" name="nomUsr" placeholder="Nombre" id="nomUsr" value="<%=u1.getNombre()%>">
                                </div>
                                <div class="form-group">
                                    <label >Escriba el apellido:</label>
                                    <input type="text" class="form-control" name="apUsr" placeholder="Apellido" id="apUsr" value="<%=u1.getApellido()%>">
                                </div>
                                <div class="form-group">
                                    <label>Ingrese la fecha de nacimiento:</label>
                                    <%
                                        String[] fechad = {"default"};
                                        String fecha = u1.getFechaDeNac().toString();
                                        fechad = fecha.split("T");
                                    %>
                                    <input type="date" class="form-control" name="fechaNac" placeholder="Fecha" id="fechaNac" value="<%= fechad[0] %>">
                                </div>
                                <input type="submit" class="btn btn-primary"></input> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
