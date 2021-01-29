
package servidor;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoCurso.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoCurso">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="APROBADO"/>
 *     &lt;enumeration value="CURSANDO"/>
 *     &lt;enumeration value="REPROBADO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoCurso")
@XmlEnum
public enum EstadoCurso {

    APROBADO,
    CURSANDO,
    REPROBADO;

    public String value() {
        return name();
    }

    public static EstadoCurso fromValue(String v) {
        return valueOf(v);
    }

}
