
package servidor;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoPrograma.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoPrograma">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="APROBADO"/>
 *     &lt;enumeration value="CURSANDO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoPrograma")
@XmlEnum
public enum EstadoPrograma {

    APROBADO,
    CURSANDO;

    public String value() {
        return name();
    }

    public static EstadoPrograma fromValue(String v) {
        return valueOf(v);
    }

}
