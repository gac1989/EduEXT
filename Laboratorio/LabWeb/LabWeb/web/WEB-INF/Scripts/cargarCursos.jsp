<script type="text/javascript">
    function cargarCursos(){
        var inst = $('#inst').val();
        console.log("El instituto seleccionado es: " + inst);
        $.ajax({
            async:false,
            type:'POST',
            data:{inst: inst},
            url:'concultacurso',
            success:function(result){
                console.log(result);
                $('#cur').html(result.result1);
                $('#docentes').html(result.result2);
            }
        });
    }
</script>