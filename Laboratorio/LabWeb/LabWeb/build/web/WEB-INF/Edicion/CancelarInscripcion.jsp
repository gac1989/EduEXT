<%-- 
    Document   : listarInscripEdic
    Created on : Oct 22, 2020, 10:10:18 AM
    Author     : Surface
--%>

<%@page import="servidor.EstadoEdicion"%>
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
                <h1 class="titulo">Cancelar inscripcion</h1><br><br>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">
                            <div>
                                <form action="CancelarInscripcion" method="post">
                                    <table name="filas" id ="filas" class="table table-bordered">
                                        <tr>
                                            <th>Seleccion</th>
                                            <th>Curso</th>
                                            <th>Edicion</th>
                                            <th>Fecha insc</th>
                                            <th>Estado</th>
                                            <th>Video motivacional</th>
                                        </tr>
                                        <%
                                            List<DtInscripcionE> datos = (List)request.getAttribute("inscripciones");
                                            if(datos!=null){
                                                for(DtInscripcionE insc : datos){
                                                    if(insc.getEstado()==EstadoInscripcion.ACEPTADA && insc.getEstadoEdicion()==EstadoEdicion.ABIERTA){
                                        %>

                                        <tr>
                                            <td><input type="radio" name="seleccion" value="<%=insc.getNombreCurso()%>,<%=insc.getNombreEdicion()%>" id="seleccion"></td>
                                            <td name="cur" id="cur"><%= insc.getNombreCurso() %></td> 
                                            <td name="edi" id="edi"><%= insc.getNombreEdicion() %></td>
                                            <td name="fech" id="fech"><%= insc.getFechaInsc()%></td>
                                            <td name="estado" id="estado"><%= insc.getEstado() %></td> 
                                            <td><iframe width="200" height="150" src="<%=insc.getVideo()%> " frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></td>
                                        </tr>
                                            <%
                                                    }
                                                }
                                            }
                                            %>  
                                    </table>   
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary"  id="boton">Enviar</button> &nbsp;
                                        <a href="Home"><input type="button" name="cancel" id="cancel" class="btn btn-default" value="Cancelar"></a>
                                    </div>
                                </form>
                            </div>    
                            <div>  
                                <br><br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
