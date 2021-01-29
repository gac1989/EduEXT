<%-- 
    Document   : Instituto
    Created on : oct 8, 2020, 11:46:15 p.m.
    Author     : admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instituto</title>
    </head>
    <body class="left-menu">        
        <h1>Ingrese los datos:</h1>     
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <form action="Instituto" method="post">                      
                                <label >Escriba el nombre del instituto:</label>
                                <input type="text" class="form-control" name="nombre" placeholder="Nombre">
                                <br>
                                <input type="submit" class="btn btn-primary"></input>
                            </form>
                        </div>                      
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
