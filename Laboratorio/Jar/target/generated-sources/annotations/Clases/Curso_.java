package Clases;

import Clases.CalifEstuCur;
import Clases.Categoria;
import Clases.Curso;
import Clases.Edicion;
import Clases.Programa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T11:37:50")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> descripcion;
    public static volatile MapAttribute<Curso, String, Curso> previas;
    public static volatile MapAttribute<Curso, String, Categoria> categorias;
    public static volatile SingularAttribute<Curso, String> imagenDir;
    public static volatile SingularAttribute<Curso, String> nombre;
    public static volatile SingularAttribute<Curso, String> URL;
    public static volatile SingularAttribute<Curso, Integer> visitas;
    public static volatile SingularAttribute<Curso, Date> registro;
    public static volatile SingularAttribute<Curso, Double> cant_horas;
    public static volatile MapAttribute<Curso, String, Edicion> ediciones;
    public static volatile MapAttribute<Curso, String, Programa> programas;
    public static volatile ListAttribute<Curso, CalifEstuCur> calific;
    public static volatile SingularAttribute<Curso, String> duracion;
    public static volatile SingularAttribute<Curso, Long> id;
    public static volatile SingularAttribute<Curso, Double> creditos;

}