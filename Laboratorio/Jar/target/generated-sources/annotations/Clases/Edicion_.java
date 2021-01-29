package Clases;

import Clases.Comentarios;
import Clases.Docente;
import Clases.InscripcionE;
import Datatypes.EstadoEdicion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T11:37:50")
@StaticMetamodel(Edicion.class)
public class Edicion_ { 

    public static volatile SingularAttribute<Edicion, EstadoEdicion> estado;
    public static volatile SingularAttribute<Edicion, Integer> cupo_max;
    public static volatile SingularAttribute<Edicion, Date> fecha_pub;
    public static volatile SingularAttribute<Edicion, Date> fecha_fin;
    public static volatile ListAttribute<Edicion, InscripcionE> inscripciones;
    public static volatile SingularAttribute<Edicion, Integer> cupo_fijo;
    public static volatile SingularAttribute<Edicion, String> imagenDir;
    public static volatile SingularAttribute<Edicion, Long> id;
    public static volatile SingularAttribute<Edicion, String> nombre;
    public static volatile SingularAttribute<Edicion, Date> fecha_ini;
    public static volatile ListAttribute<Edicion, Comentarios> comentarios;
    public static volatile MapAttribute<Edicion, String, Docente> docentes;

}