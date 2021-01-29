

<html>
    <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#but').on("click",function(){
                console.log("Funcionan los scripts partidos");
            });
        });
    </script>
    </head>
    <body>     
        <div class="menu-search">
            <form action="Busqueda" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="¿Qué estás buscando?" id="busqueda" name="busqueda">
                    <button class="btn btn-primary"><i class="fa fa-search"></i></button>
                </div>
            </form>
        </div><!-- end menu-search -->
    </body>
</html>
 