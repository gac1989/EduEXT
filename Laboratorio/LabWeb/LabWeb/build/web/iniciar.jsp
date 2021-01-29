<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script>
            $(document).ready( function(){
                if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
                    console.log("MOBILEEEE");
                    window.location="iniciar?mobile="+"true";
                }   
                else{
                    console.log("NO MOBILEEEE");
                    window.location="iniciar?mobile="+"false";
                }
            });
        </script>
    </head>
    <body>
         
    </body>
</html>
