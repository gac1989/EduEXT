<script>
    function cargarCursosCat(){
        var cat = $('#cat').val();
        console.log("la categoria seleccionada es: " + cat);
        $.ajax({
            type:'POST',
            data:{cat: cat},
            url:'cursosCategoria',
            success:function(result){
                console.log(result);
                $('#cur').html(result.result1);
            }
        });
    }
</script>