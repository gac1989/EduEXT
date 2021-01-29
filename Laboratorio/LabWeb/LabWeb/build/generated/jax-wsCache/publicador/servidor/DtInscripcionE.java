
package servidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtInscripcionE complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtInscripcionE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha_insc" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecha_aprobado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="est" type="{http://PRESENTACION/}dtUsuario" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://PRESENTACION/}estadoInscripcion" minOccurs="0"/>
 *         &lt;element name="nombreEdicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nota" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="estadoEdicion" type="{http://PRESENTACION/}estadoEdicion" minOccurs="0"/>
 *         &lt;element name="estadoCurso" type="{http://PRESENTACION/}estadoCurso" minOccurs="0"/>
 *         &lt;element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtInscripcionE", propOrder = {
    "fechaInsc",
    "fechaAprobado",
    "est",
    "estado",
    "nombreEdicion",
    "nombreCurso",
    "nota",
    "estadoEdicion",
    "estadoCurso",
    "video"
})
public class DtInscripcionE {

    @XmlElement(name = "fecha_insc")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInsc;
    @XmlElement(name = "fecha_aprobado")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAprobado;
    protected DtUsuario est;
    protected EstadoInscripcion estado;
    protected String nombreEdicion;
    protected String nombreCurso;
    protected int nota;
    protected EstadoEdicion estadoEdicion;
    protected EstadoCurso estadoCurso;
    protected String video;

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
     * Obtiene el valor de la propiedad fechaAprobado.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAprobado() {
        return fechaAprobado;
    }

    /**
     * Define el valor de la propiedad fechaAprobado.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAprobado(XMLGregorianCalendar value) {
        this.fechaAprobado = value;
    }

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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoInscripcion }
     *     
     */
    public EstadoInscripcion getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoInscripcion }
     *     
     */
    public void setEstado(EstadoInscripcion value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEdicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEdicion() {
        return nombreEdicion;
    }

    /**
     * Define el valor de la propiedad nombreEdicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEdicion(String value) {
        this.nombreEdicion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCurso() {
        return nombreCurso;
    }

    /**
     * Define el valor de la propiedad nombreCurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCurso(String value) {
        this.nombreCurso = value;
    }

    /**
     * Obtiene el valor de la propiedad nota.
     * 
     */
    public int getNota() {
        return nota;
    }

    /**
     * Define el valor de la propiedad nota.
     * 
     */
    public void setNota(int value) {
        this.nota = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoEdicion.
     * 
     * @return
     *     possible object is
     *     {@link EstadoEdicion }
     *     
     */
    public EstadoEdicion getEstadoEdicion() {
        return estadoEdicion;
    }

    /**
     * Define el valor de la propiedad estadoEdicion.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoEdicion }
     *     
     */
    public void setEstadoEdicion(EstadoEdicion value) {
        this.estadoEdicion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoCurso.
     * 
     * @return
     *     possible object is
     *     {@link EstadoCurso }
     *     
     */
    public EstadoCurso getEstadoCurso() {
        return estadoCurso;
    }

    /**
     * Define el valor de la propiedad estadoCurso.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoCurso }
     *     
     */
    public void setEstadoCurso(EstadoCurso value) {
        this.estadoCurso = value;
    }

    /**
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Define el valor de la propiedad video.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

}
