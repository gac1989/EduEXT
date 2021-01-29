 <script>
        function cargarDatos(){
            var cur = $('#cur').val();
            console.log("El curso seleccionado es: " + cur);
            $.ajax({
                type:'POST',
                data:{cur: cur},
                url:'cursodatos',
                success:function(result){
                    console.log(result);
                    $('#nombre').html(result.nombre);
                    $('#descripcion').html(result.descripcion);
                    $('#duracion').html(result.duracion);
                    $('#horas').html(result.horas);
                    $('#creditos').html(result.creditos);
                    $('#fechaReg').html(result.fechaReg);
                    $('#url').html(result.url);
                    $('#calif').html(result.calificacion);
                    $('#edi').html(result.result1);
                    $('#prog').html(result.result2);
                    $('#result').html(result.result3);
                }
            });
        }
</script>