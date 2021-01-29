<%-- 
    Document   : listar
    Author     : Igui
--%>
<%@page import="servidor.DtUsuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
	<title>Usuarios</title>
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
                            <% 
                                List<DtUsuario> usuarios = (List)request.getAttribute("usuarios");
                                for(DtUsuario usuario: usuarios){
                            %>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><a href="consultausuario?nick=<%=usuario.getNick()%>"><%=usuario.getNick()%></a></h3>
                                </div>
                                <div class="panel-body">
                                    <h3 id="nombre"><%=usuario.getCorreo()%></h3>
                                </div>
                            </div>
                            <%      } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>