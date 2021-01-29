
package servidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtInscripcionP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtInscripcionP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="est" type="{http://PRESENTACION/}dtUsuario" minOccurs="0"/>
 *         &lt;element name="estadoPrograma" type="{http://PRESENTACION/}estadoPrograma" minOccurs="0"/>
 *         &lt;element name="fecha_insc" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecha_aprob" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtInscripcionP", propOrder = {
    "est",
    "estadoPrograma",
    "fechaInsc",
    "fechaAprob"
})
public class DtInscripcionP {

    protected DtUsuario est;
    protected EstadoPrograma estadoPrograma;
    @XmlElement(name = "fecha_insc")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInsc;
    @XmlElement(name = "fecha_aprob")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAprob;

    /**
     * Obtiene el valor de la propiedad est.
     * 
     * @return
     *     possible object is
     *     {@link DtUsuario }
     *     
     */
    public DtUsuario getEst() {
        return est;
    }

    /**
     * Define el valor de la propiedad est.
     * 
     * @param value
     *     allowed object is
     *     {@link DtUsuario }
     *     
     */
    public void setEst(DtUsuario value) {
        this.est = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoPrograma.
     * 
     * @return
     *     possible object is
     *     {@link EstadoPrograma }
     *     
     */
    public EstadoPrograma getEstadoPrograma() {
        return estadoPrograma;
    }

    /**
     * Define el valor de la propiedad estadoPrograma.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoPrograma }
     *     
     */
    public void setEstadoPrograma(EstadoPrograma value) {
        this.estadoPrograma = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInsc.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInsc() {
        return fechaInsc;
    }

    /**
     * Define el valor de la propiedad fechaInsc.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInsc(XMLGregorianCalendar value) {
        this.fechaInsc = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAprob.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAprob() {
        return fechaAprob;
    }

    /**
     * Define el valor de la propiedad fechaAprob.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAprob(XMLGregorianCalendar value) {
        this.fechaAprob = value;
    }

}
