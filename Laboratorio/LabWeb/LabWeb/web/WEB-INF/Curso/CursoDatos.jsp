<%-- 
    Document   : consultacurso
    Created on : oct 16, 2020, 4:19:29 p.m.
    Author     : admin
--%>

<%@page import="servidor.DtCurso"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de curso</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <style>
            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #f1f1f1;
            }

                /* Style the buttons that are used to open the tab content */
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
            }

                /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

                /* Create an active/current tablink class */
            .tab button.active {
                background-color: #ccc;
            }

                /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            }

        </style>
    </head>
    <body class="left-menu">
        <% 
            DtCurso curso1 = (DtCurso)request.getAttribute("curso"); 
            if(curso1!=null){
                
        %>
        
        <div class="menu-wrapper">
            <jsp:include page="/WEB-INF/templates/panel.jsp"/>
        </div>
        
        <div id="wrapper">
            <div id="home" class="video-section js-height-full">           
                <h1 class="titulo">Consulta de curso</h1>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-sm">      
                            
        <%};%>                  
                            <%  
                                String nombre="";
                                String descripcion="";
                                String duracion="";
                                Double horas=0.0;
                                Double creditos=0.0;
                                String fechareg="";
                                String url="";
                                String imagen="";
                                if(curso1!=null){
                                    nombre=curso1.getNombre();
                                    duracion=curso1.getDuracion();
                                    descripcion=curso1.getDescripcion();
                                    horas=curso1.getCantHoras();
                                    creditos=curso1.getCantCred();
                                    fechareg=curso1.getFechaReg().toString();
                                    url=curso1.getUrl();
                                    imagen=curso1.getImagenDir();
                                }
                            %>
                       
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
