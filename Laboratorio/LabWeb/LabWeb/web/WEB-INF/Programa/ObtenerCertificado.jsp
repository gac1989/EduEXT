<%-- 
    Document   : ObtenerCertificado
    Created on : 26/11/2020, 02:39:49 PM
    Author     : Surface
--%>

<%@page import="servidor.EstadoInscripcion"%>
<%@page import="servidor.DtInscripcionE"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.0/jspdf.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#pdfview').on("click",function () {
                    var pdf = new jsPDF('l', 'in', [8, 11]);
                    pdf.internal.scaleFactor = 50;            
                    var lol = $('#content')[0]; 
                    pdf.addHTML(lol, function () {
                        pdf.save('Test.pdf');
                    });
                    pdf.addHTML($('#content2')[0], function () {
                        pdf.save('Planilla.pdf');
                    });
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <title>Inscripción a programa</title>
        <script>
            $(document).ready(function(){
                $('#pdfdiv').css("display", "none");
                $('#pdfdiv2').css("display", "none");
                $('#pdfview').css("display", "none");
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#prog').on("click", function(){
                    var prog = $('#prog').val();
                    $.ajax({
                        type:'POST',
                        data:{prog: prog},
                        url:'ObtenerCertificado',
                        success:function(result){
                            console.log(result);
                            $('#mensaje').html(result.result1);
                            $('#filas').html(result.result2);
                            $('#filas2').html(result.result2);
                            if(result.result1=="APROBADO"){
                                $('#pdfdiv').html(result.result3);
                                $('#pdfdiv').css("display", "block");
                                $('#pdfdiv2').css("display", "block");
                                $('#pdfview').css("display", "inline");
                            }
                            else{
                                $('#pdfview').css("display", "none");
                                $('#pdfdiv').css("display", "none");
                                $('#pdfdiv2').css("display", "none");
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
                <h1 class="titulo">Obtener certificado de Programa</h1>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">
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
                            <h1 class="titulo">Mostrar resultados</h1><br><br>
                            <div class="form-group">
                                <table name="filas" id ="filas" class="table table-bordered">
                                    <tr>
                                        <th>Curso</th>
                                        <th>Edicion</th>
                                        <th>Fecha insc</th>
                                        <th>Estado</th>
                                        <th>Nota</th>
                                    </tr>
                                </table>    
                            </div>
                            <div id="pdfdiv">
                            </div>
                            <div id="pdfdiv2">
                                <div id="content2" style="width:800px; height:600px; padding:20px; text-align:center; border: 10px solid #787878; background-color: white;" >
                                    <div style="width:750px; height:550px; padding:20px; text-align:center; border: 5px solid #787878">
                                        <table name="filas2" id ="filas2" class="table table-bordered">
                                            <tr>
                                                <th>Curso</th>
                                                <th>Edicion</th>
                                                <th>Fecha insc</th>
                                                <th>Estado</th>
                                                <th>Nota</th>
                                            </tr>
                                        </table>  
                                    </div>
                                </div>
                            </div>
                            <div id="editor"></div>
                            <br>
                            <div class="form-group">  
                                <a><input type="button" id="pdfview" class="btn btn-primary" value="Imprimir"></a>
                                <a href="Home"><input type="button" class="btn btn-default" value="Cancelar"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
