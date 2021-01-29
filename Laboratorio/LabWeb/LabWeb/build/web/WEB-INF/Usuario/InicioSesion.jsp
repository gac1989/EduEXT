<%-- 
    Document   : InicioSesion
    Created on : 10/10/2020, 03:37:11 PM
    Author     : arena
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
          <script>
            function validacion(){
                var nick = document.getElementById('login').value;
                var password = document.getElementById('password').value;
                if(!nick || !password){
                    alert("INGRESE TODOS LOS DATOS");
                    return false;
                } 
                else{
                    return true;
                }
            }
        </script>
        <meta charset="utf-8">
        <title>Login </title>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">     
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>        
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">        
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">         
                            <h1 class="titulo">Inicio de Sesión</h1>
                            <form onsubmit="return validacion()">
                                <div class="form-group">
                                    <label for="username">Nick</label><br>
                                    <input type="text" placeholder="Enter Username" name="login" id="login">
                                </div>
                                <!-- PASSWORD INPUT -->
                                <div class="form-group">
                                    <label for="password">Contrasena</label><br>
                                    <input type="password" placeholder="Enter Password" name="password" id="password"><br><br>
                                    <button type="submit" class="btn btn-primary"  id="boton">Login</button> &nbsp;
                                    <a href="Home" id="cancel" name="cancel" class="btn btn-default">Cancel</a>
                                    <a href="Usuario">¿No tienes una cuenta?</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
