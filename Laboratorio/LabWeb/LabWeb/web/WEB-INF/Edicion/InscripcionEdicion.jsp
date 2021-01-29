<%-- 
    Document   : InscripcionEdicion
    Created on : Oct 16, 2020, 3:59:54 PM
    Author     : Surface
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/Scripts/Pestana.jsp"/>
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarCursos.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarEdiciones.jsp"/>
        <jsp:include page="/WEB-INF/Scripts/cargarCursosCat.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Inscripción a edición</title>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#inst').on("click", cargarCursos);  
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#cat').on("click", cargarCursosCat);  
            });
        </script>
        <script>
            function cargarDatosEdicion(){
                var edi = $('#edi').html();
                console.log("La edicion vigente es: " + edi);
                var cur = $('#cur').val();
                $.ajax({
                    async: false,
                    type:'POST',
                    data:{edi: edi, cur: cur},
                    url:'ediciondatos',
                    success:function(result){
                        console.log(result);
                        $('#nombre').html(result.nombre);
                        $('#fechaini').html(result.fechaini);
                        $('#fechafin').html(result.fechafin);
                        $('#cuposmax').html(result.cuposmax);
                        $('#fechapub').html(result.fechapub);
                        $('#docentes').html(result.result1);
                        var msg = result.mensaje;
                        if(msg=="Ya esta inscripto"){
                            $('#res').css("display", "inline");
                            $('#res').html("Ya esta inscripto");
                            $('#boton').css("display", "none");
                        }
                        else{
                            $('#res').html("");
                            $('#boton').css("display", "inline");
                        }
                        $('#mensaje').html(result.mensaje);
                    }
                });
            }
        </script>
        <script>
            function cargarEdicionVigente(){
                var curso = $('#cur').val();
                if(curso=="Seleccione un curso"){
                    return;
                }
                var nick="<%=(String)request.getSession().getAttribute("usuario_logueado") %>";
                var check=true;
                $.ajax({
                    async: false,
                    type:'POST',
                    data:{curso: curso, nick: nick},
                    url:'checkPrevias',
                    success:function(result){
                        if(!result.check){
                            alert("EL USUARIO NO HA COMPLETADO LAS MATERIAS PREVIAS");
                            $('#boton').css("display", "none");
                            check=result.check;
                            return;
                        }
                        else{
                            $('#boton').css("display", "inline");
                        }
                    }
                });
                if(check){
                    console.log("El curso seleccionado es: " + cur);
                    $.ajax({
                        async: false,
                        type:'POST',
                        data:{curso: curso},
                        url:'EdicionVigente',
                        success:function(result){
                            if(result.edvig){
                                $('#edi').html(result.edvig);
                                var edi = $('#edi').html();
                                var valor = document.getElementById("edih");
                                valor.value=edi;
                                console.log("Se logro poner el valor" + valor.value);
                                $('#boton').css("display", "inline");
                                cargarDatosEdicion();
                            }
                            else{
                                $('#edi').html("NO HAY EDICIONES VIGENTES PARA LA INSCRIPCION");
                                $('#boton').css("display", "none");
                            }
                        }
                    });
                }
            }  
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#cur').on("change", cargarEdicionVigente);  
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#edi').on("click", );  
            });
        </script>
             <script>
            function myFunction() {
                var str = $('#video').val();
                var res = str.split("=");
                var res2 = res[1].split("&");
                var embeddedUrl = "https://www.youtube.com/embed/"+res2[0];
                var video = document.getElementById("video");
                video.value=embeddedUrl;
                return true;
            }
        </script>
    </head>
    <body class="left-menu">
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">
                <h1 class="titulo">Inscripción a edición de curso</h1>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">
                            <form action="InscripcionEdicion" method="get" id="insc" onsubmit="return myFunction()">
                                <div class="form-group">
                                    <p>Institutos:</p>               
                                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                    <select id="inst" name="inst">
                                        <c:forEach items="${institutos}" var="inst" varStatus="loop">
                                            <option>
                                                ${inst}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">  
                                    <p>Categoria:<p>
                                    <select id="cat" name="cat" >
                                        <c:forEach items="${categorias}" var="cat" varStatus="loop">
                                            <option>
                                                ${cat}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>     
                                <div class="form-group">  
                                    <p>Curso:</p>
                                    <select id="cur" name="cur">
                                    </select>
                                </div>
                                <input type="hidden" id="edih" name="edih" value="3487">
                                <div class="form-group">  
                                    <p>Edicion vigente:&nbsp;&nbsp;<span id="edi" name="edi"></span></p>
                                </div>
                                <div class="form-group">
                                    <label>Ingrese el link de su video motivacional:</label>
                                    <input type="text" class="form-control" name="video" placeholder="URL" id="video">
                                </div>
                            </form>
                            <div class="tab">
                                <button class="tablinks" onclick="pestana(event, 'general')" id="defaultOpen" name="defaultOpen">General</button>
                                <button class="tablinks" onclick="pestana(event, 'docentes')">Docentes</button>
                            </div>
                            <div id="general" class="tabcontent">
                                <h3 class="titulo">Nombre:</h3><h3 id="nombre">&nbsp;&nbsp; </h3>
                                <h3 class="titulo">Cupos:</h3><h3 id="cuposmax"> &nbsp;&nbsp;</h3>
                                <h3 class="titulo">Fecha inicio:</h3><h3 id="fechaini">&nbsp;&nbsp;</h3>
                                <h3 class="titulo">Fecha fin:</h3><h3 id="fechafin">&nbsp;&nbsp; </h3>
                                <h3 class="titulo">Fecha de publicacion:</h3><h3 id="fechapub">&nbsp;&nbsp;</h3>
                            </div>
                            <div id="docentes" class="tabcontent">
                                <div id="doc" name="doc">
                                </div>
                            </div>
                            <div class="form-group">
                                <span style="color:red" id="res"></span>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" value="Inscribirse" form="insc" id="boton"></input>
                                <a href="Home"><input type="button" class="btn btn-default" value="Cancelar"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
          function pestana(evt, cityName) {
            // Declare all variables
            var i, tabcontent, tablinks;

            // Get all elements with class="tabcontent" and hide them
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }

            // Get all elements with class="tablinks" and remove the class "active"
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }

            // Show the current tab, and add an "active" class to the button that opened the tab
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " active";
        }
        document.getElementById("defaultOpen").click();
        
        </script>
    </body>
</html>
