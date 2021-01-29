<%-- 
    Document   : informacion
    Created on : oct 23, 2020, 11:20:59 a.m.
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Busqueda</title>
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
                            <h1 style="text-decoration: underline">Resultados:</h1>
                                <% 
                                    String resultado = (String)request.getAttribute("resultado");
                                    out.println(resultado);
                                %>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- end wrapper -->
    </body>
</html>
