<%-- 
    Document   : valorarCurso
    Created on : 16/11/2020, 10:35:31 PM
    Author     : Surface
--%>

<%@page import="java.util.List"%>
<%@page import="servidor.DtCurso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Valorar un Curso</title>
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#cur').on("click", function(){
                    var cur = $('#cur').val();
                    console.log("El curso seleccionado es: " + cur);
                    $.ajax({
                        type:'POST',
                        data:{cur: cur},
                        url:'valorarCursoEst',
                        success:function(result){
                            console.log(result);
                            $('#calif').html(result.result1);
                            if(result.result2){
                                $('#res3').css("display", "block");
                                $('#res3').html("Ya valoró el curso, con: " + result.result3 + " puntos.");
                                $('#boton').css("display", "none");
                                $('#estrella').css("display", "none");
                            }else{
                                $('#res3').html("");
                                $('#boton').css("display", "inline");
                                $('#estrella').css("display", "inline");
                            }
                        }
                    });
                });  
            });
        </script>
        <style>
                        *{
                margin: 0;
                padding: 0;
            }
            .rate {
                float: left;
                height: 46px;
                padding: 0 10px;
            }
           
            .rate:not(:checked) > input {
                position:absolute;
                top:-9999px;
            }
            .rate:not(:checked) > label {
                float:right; 
                width:1em;
                overflow:hidden;
                white-space:nowrap;
                cursor:pointer;
                font-size:30px;
                color:#ccc;
            }
            .rate:not(:checked) > label:before {
                content: '★ ';
            }
            .rate > input:checked ~ label {
                color: #ffc700;    
            }
            .rate:not(:checked) > label:hover,
            .rate:not(:checked) > label:hover ~ label {
                color: #deb217;  
            }
            .rate > input:checked + label:hover,
            .rate > input:checked + label:hover ~ label,
            .rate > input:checked ~ label:hover,
            .rate > input:checked ~ label:hover ~ label,
            .rate > label:hover ~ input:checked ~ label {
                color: #c59b08;
            }
        </style>
    </head>
    <body class="left-menu">
        
       
        <form action="valorarCursoEst" method="get">
            <div id="wrapper">
            <div id="home" class="video-section js-height-full"> 
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">  
                            <div class="form-group">  
                                    <div class="form-group">
                                        <h1>Usted va a valorar un curso, tomelo con seriedad</h1>
                                        <br>
                                        <label>Seleccione el curso:</label>
                                        <select id="cur" name="cur">
                                        <%
                                            List<DtCurso> cursos = (List)request.getAttribute("cursos");
                                            for (DtCurso curso : cursos){ 
                                        %>    

                                            <option><%= curso.getNombre() %></option>


                                        <%      } %> 
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Seleccione la nota:</label>
                                        <br>
                                        <div class="rate" id="estrella">
                                                <input type="radio" id="star5" name="nota" value="5" />
                                                <label for="star5" title="text">5 stars</label>
                                                <input type="radio" id="star4" name="nota" value="4" />
                                                <label for="star4" title="text">4 stars</label>
                                                <input type="radio" id="star3" name="nota" value="3" />
                                                <label for="star3" title="text">3 stars</label>
                                                <input type="radio" id="star2" name="nota" value="2" />
                                                <label for="star2" title="text">2 stars</label>
                                                <input type="radio" id="star1" name="nota" value="1" />
                                                <label for="star1" title="text">1 star</label>
                                            </div>
                                        <br>
                                        <br>
                                    </div>

                                    <div class="form-group">
                                        <label>Nota del curso:</label>
                                        <span name="calif" id="calif"> </span>
                                    </div>
                                     <div class="form-group">
                                        
                                        <span style ="color: red" name="res3" id="res3"> </span>
                                    </div>    
                                    <div class="form-group">
                                         <input type="submit" class="btn btn-primary" value="Valorar" id="boton"></input>
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
