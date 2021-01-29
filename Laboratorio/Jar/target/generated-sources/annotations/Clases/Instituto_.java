package Clases;

import Clases.Curso;
import Clases.Docente;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T11:37:50")
@StaticMetamodel(Instituto.class)
public class Instituto_ { 

    public static volatile MapAttribute<Instituto, String, Curso> cursos;
    public static volatile SingularAttribute<Instituto, Long> id;
    public static volatile SingularAttribute<Instituto, String> nombre;
    public static volatile MapAttribute<Instituto, String, Docente> docentes;

}