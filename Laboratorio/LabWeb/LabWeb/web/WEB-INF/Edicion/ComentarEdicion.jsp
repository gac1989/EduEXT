<%-- 
    Document   : ComentarEdicion
    Created on : 19/11/2020, 03:14:51 PM
    Author     : Surface
--%>

<%@page import="servidor.DatosEdicion"%>
<%@page import="servidor.DtEdicion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentar una edicion</title>
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#edi').on("click", function(){
                    var edi = $('#edi').val();
                    console.log("La edicion seleccionada es: " + edi);
                    $.ajax({
                        async: false,
                        type:'POST',
                        data:{edi: edi},
                        url:'ComEdicion',
                        success:function(result){
                            console.log(result);
                            $('#coments').html(result.result);
                        }
                    });
                });  
            });
        </script> 
        <script>
            function enviarRespuesta(id){
                var com = document.getElementById(id);
                var edi = $('#edi').val();
                console.log(com.value);
                $.ajax({
                    async: false,
                    type:'POST',
                    data:{res: id, com: com.value, edi: edi},
                    url:'responderComentario',
                    success:function(result){
                        
                    }
                });
                $('#edi').click();
            }
        </script>
        <script>
            function mostrarCampo(id){
                var com = document.getElementById(id);
                $('#'+id).css("display", "block");
                console.log(com.value);
            }
        </script>
        <script>
            function actualizar(){
                $('#edi').click();
            }
        </script>
    </head>
    <body class="left-menu">
        <form action="ComEdicion" method="get">
            <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <div class="form-group">  
                                    <div class="form-group">
                                        <h1>Usted va a comentar una edicion, tomelo con seriedad</h1>
                                        <br>
                                        <label>Seleccione la edicion:</label>
                                        <select id="edi" name="edi">
                                        <%
                                            List<DatosEdicion> ediciones = (List)request.getAttribute("ediciones");
                                            for (DatosEdicion edicion : ediciones){ 
                                        %>    

                                            <option><%= edicion.getEdicion() %></option>


                                        <%      } %> 
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Escriba un comentario:</label>
                                        <input type="text" id="coment" name="coment">
                                    </div>
                                        <div class="form-group">
                                           <label>Comentarios:</label>
                                            <div id="coments" name="coments">
                                  
                                            </div>
                                        </div>
                                    <div class="form-group">
                                         <input type="submit" class="btn btn-primary" value="Comentar" id="boton" onclick="actualizar()"></input>
                                         <a href="Home"><input type="button" class="btn btn-default" value="Cancelar"></a>
                                    </div>
                                </div>
                            </div>
                         </div>
                      </div>
                </div>
           </div>
        </form> 
    </body>
</html>

