<%-- 
    Document   : InscripcionProg
    Created on : Oct 18, 2020, 5:12:29 PM
    Author     : Surface
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Inscripción a programa</title>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#prog').on("click", function(){
                    var prog = $('#prog').val();
                    $.ajax({
                        type:'POST',
                        data:{prog: prog},
                        url:'InscripcionProg',
                        success:function(result){
                            console.log(result);
                            if(result.mensaje=="Ya esta inscripto"){
                                $('#mensaje').html(result.mensaje);
                                $('#boton').css("display","none");
                            }
                            else{
                                $('#mensaje').html(result.mensaje);
                                $('#boton').css("display","inline");
                            }
                            
                        }
                    });
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
                <h1 class="titulo">Inscripción a programa de formación</h1>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">
                            <form action="InscripcionProg" method="get">
                                <div class="form-group">
                                    <label>Programas:</label><br>               
                                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <select id="prog" name="prog">
                                        <c:forEach items="${programas}" var="prog" varStatus="loop">
                                            <option>
                                                ${prog}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Mensaje:</label> <h2 name="mensaje" id="mensaje"></h2>
                                </div>            
                                <input type="submit" class="btn btn-primary" value="Inscribirse" id="boton"></input>
                                <a href="Home"><input type="button" class="btn btn-default" value="Cancelar"></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
