<script>
    function cargarEdiciones(){
        var cur = $('#cur').val();
        if(cur=="Seleccione un curso"){
            return;
        }
        console.log("El curso seleccionado es: " + cur);
        $.ajax({
            async: false,
            type:'POST',
            data:{cur: cur},
            url:'edicionCurso',
            success:function(result){
                console.log(result);
                $('#edi').html(result.result1);
            }
        });
    }  
</script>