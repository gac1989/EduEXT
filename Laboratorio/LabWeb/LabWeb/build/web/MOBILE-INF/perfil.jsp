<%@page import="controller.EstadoSesion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.text.SimpleDateFormat"%>
<!doctype html>
<html>
    <head>
        <jsp:include page="/WEB-INF/Scripts/Pestana.jsp"/>
	<title>Perfil </title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class="left-menu">
        <% servidor.DtUsuario usr = (servidor.DtUsuario) request.getAttribute("usuario"); %>
        <div class="menu-wrapper">     
            <jsp:include page="/MOBILE-INF/panel_m_estudiante.jsp"/>
        </div> 
        <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <%if(usr!=null){%>
                            <div class="tab">
                                <button class="tablinks" onclick="pestana(event, 'general')" id="defaultOpen" name="defaultOpen">General</button>
                                <button class="tablinks" onclick="pestana(event, 'ediciones')">Ediciones</button>
                                <button class="tablinks" onclick="pestana(event, 'programas')">Programas</button>
                            </div>
                            <div id="general" class="tabcontent">
                                <div class="form-group">
                                    <h2  class="titulo" style="color: red"><%=usr.getNick()%></h2>
                                    <img src="<%= usr.getImagenDir() %>" >
                                </div>
                                <h3 class="titulo">Nombre:</h3><h3 id="nombre">&nbsp;&nbsp;<%=usr.getNombre()%></h3>
                                <h3 class="titulo">Apellido:</h3><h3 id="descripcion">&nbsp;&nbsp;<%=usr.getApellido()%> </h3>
                                <h3 class="titulo">Correo:</h3><h3 id="fechaini">&nbsp;&nbsp;<%=usr.getCorreo()%> </h3>
                                <h3 class="titulo">Fecha de nacimiento:</h3><h3 id="fechafin">&nbsp;&nbsp;<%=usr.getFechaDeNac()%></h3>
                            </div>
                            <div id="ediciones" class="tabcontent">
                                <%
                                    String nick2 = (String) request.getSession().getAttribute("usuario_logueado");
                                    ArrayList<servidor.DatosEdicion> edi = (ArrayList)request.getAttribute("ediciones");
                                    if(edi!=null){
                                        for(servidor.DatosEdicion ed : edi){
                                %>
                                <a href="consultaediciondecurso?inst=<%=ed.getInstituto()%>&cur=<%=ed.getCurso()%>&ed=<%= ed.getEdicion()%>"><%=ed.getEdicion()%></a>&nbsp;&nbsp;<br>
                                <%
                                        }
                                    }
                                %>
                            </div>
                            <div id="programas" class="tabcontent">
                                <%
                                    ArrayList<String> prog = (ArrayList)request.getAttribute("programas");
                                    if(prog!=null){
                                        for(String pr : prog){
                                %>
                                            <a href="consultaPrograma?prog=<%=pr%>"><%=pr%></a><br>
                                <%
                                        }
                                    }
                                %>
                            </div>
                            <%};%>
                        </div>
                        <div class="form-group">    
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/Scripts/scriptPestana.jsp"/>
    </body>
</html>