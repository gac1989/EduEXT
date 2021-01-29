package Clases;

import Clases.Estudiante;
import Datatypes.EstadoCurso;
import Datatypes.EstadoInscripcion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T11:37:50")
@StaticMetamodel(InscripcionE.class)
public class InscripcionE_ { 

    public static volatile SingularAttribute<InscripcionE, EstadoInscripcion> estado;
    public static volatile SingularAttribute<InscripcionE, Date> fecha_insc;
    public static volatile SingularAttribute<InscripcionE, Estudiante> est;
    public static volatile SingularAttribute<InscripcionE, EstadoCurso> estadoCurso;
    public static volatile SingularAttribute<InscripcionE, Long> id;
    public static volatile SingularAttribute<InscripcionE, Date> fecha_aprobado;
    public static volatile SingularAttribute<InscripcionE, String> video;
    public static volatile SingularAttribute<InscripcionE, Integer> nota;

}