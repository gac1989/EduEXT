<script>
    function cargarEdicionesDocente(){
        var cur = $('#cur').val();
        console.log("El curso seleccionado es: " + cur);
        $.ajax({
            async: false,
            type:'GET',
            data:{cur: cur},
            url:'edicionCurso',
            success:function(result){
                console.log(result);
                $('#edi').html(result.result1);
            }
        });
    }  
</script>