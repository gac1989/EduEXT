<%-- 
    Document   : listarInscripEdic
    Created on : Oct 22, 2020, 10:10:18 AM
    Author     : Surface
--%>

<%@page import="servidor.EstadoInscripcion"%>
<%@page import="servidor.DtInscripcionE"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar inscripciones</title>      
    </head>
    <body class="left-menu">        
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">      
                <h1 class="titulo">Mostrar resultados</h1><br><br>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">
                            <div>
                                <table name="filas" id ="filas" class="table table-bordered">
                                    <tr>
                                        <th>Curso</th>
                                        <th>Edicion</th>
                                        <th>Fecha insc</th>
                                        <th>Estado edicion</th>
                                        <th>Estado curso</th>
                                        <th>Nota</th>
                                    </tr>
                                    <%
                                        List<DtInscripcionE> datos = (List)request.getAttribute("inscripciones");
                                        if(datos!=null){
                                            for(DtInscripcionE insc : datos){
                                                if(insc.getEstado()==EstadoInscripcion.ACEPTADA){
                                    %>
                                    <tr>
                                        <td name="cur" id="cur"><%= insc.getNombreCurso() %></td> 
                                        <td name="edi" id="edi"><%= insc.getNombreEdicion() %></td>
                                        <td name="fech" id="fech"><%= insc.getFechaInsc()%></td>
                                        <td name="estado" id="estado"><%= insc.getEstadoEdicion()%></td>
                                        <td name="estado" id="estado"><%= insc.getEstadoCurso() %></td>
                                        <% 
                                        String nota="";
                                        if(insc.getNota()==0){
                                            nota="PENDIENTE";
                                        }
                                        else{
                                            nota=Integer.toString(insc.getNota());
                                        }
                                        %>
                                        <td name="nota" id="nota"><%=nota%></td>
                                    </tr>
                                        <% 
                                                }
                                            }
                                        }
                                        %>  
                                </table>    
                            </div>    
                            <div>  
                                <br><br>
                                <a href="Home"><input type="button" class="btn btn-primary" value="Volver"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
