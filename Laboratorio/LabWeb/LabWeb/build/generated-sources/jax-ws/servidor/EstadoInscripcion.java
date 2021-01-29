
package servidor;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoInscripcion.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoInscripcion">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACEPTADA"/>
 *     &lt;enumeration value="RECHAZADA"/>
 *     &lt;enumeration value="PENDIENTE"/>
 *     &lt;enumeration value="CANCELADA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoInscripcion")
@XmlEnum
public enum EstadoInscripcion {

    ACEPTADA,
    RECHAZADA,
    PENDIENTE,
    CANCELADA;

    public String value() {
        return name();
    }

    public static EstadoInscripcion fromValue(String v) {
        return valueOf(v);
    }

}
