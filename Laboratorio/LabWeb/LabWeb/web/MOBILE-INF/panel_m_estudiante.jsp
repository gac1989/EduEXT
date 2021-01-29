 <head>
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
            <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet"> 

            <!-- Custom & Default Styles -->
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/carousel.css">
        <link rel="stylesheet" href="./style.css">
</head>
<div class="mobile-menu">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Home"><img src="images/logo-normal.png" alt=""></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Usuario <span class="fa fa-angle-down"></span></a>
                        <ul class="dropdown-menu">
                            <% String user = (String) request.getSession().getAttribute("usuario_logueado"); 
                            if(user!=null){
                            %>
                                <li><a href="consultausuario?nick=<%=user%>">Consulta usuario</a></li>
                            <%}%>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cursos<span class="fa fa-angle-down"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="concultacurso">Consulta de curso</a></li>
                            <li><a href="consultaediciondecurso">Consulta Edicion de curso</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Programas <span class="fa fa-angle-down"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="consultaPrograma">Consulta de programa de formacion</a></li>
                        </ul>
                    </li>
                </ul> 
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/carousel.js"></script>
<script src="js/parallax.js"></script>
<script src="js/rotate.js"></script>
<script src="js/custom.js"></script>
<script src="js/masonry.js"></script>
<script src="js/masonry-4-col.js"></script>
<!-- VIDEO BG PLUGINS -->
<script src="videos/libs/swfobject.js"></script> 
<script src="videos/libs/modernizr.video.js"></script> 
<script src="videos/libs/video_background.js"></script> 