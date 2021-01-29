
package servidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtCurso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtCurso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cant_horas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="cant_cred" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fecha_reg" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imagenDir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCurso", propOrder = {
    "nombre",
    "descripcion",
    "duracion",
    "cantHoras",
    "cantCred",
    "fechaReg",
    "url",
    "imagenDir",
    "visitas"
})
public class DtCurso {

    protected String nombre;
    protected String descripcion;
    protected String duracion;
    @XmlElement(name = "cant_horas")
    protected double cantHoras;
    @XmlElement(name = "cant_cred")
    protected double cantCred;
    @XmlElement(name = "fecha_reg")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaReg;
    protected String url;
    protected String imagenDir;
    protected int visitas;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad duracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Define el valor de la propiedad duracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuracion(String value) {
        this.duracion = value;
    }

    /**
     * Obtiene el valor de la propiedad cantHoras.
     * 
     */
    public double getCantHoras() {
        return cantHoras;
    }

    /**
     * Define el valor de la propiedad cantHoras.
     * 
     */
    public void setCantHoras(double value) {
        this.cantHoras = value;
    }

    /**
     * Obtiene el valor de la propiedad cantCred.
     * 
     */
    public double getCantCred() {
        return cantCred;
    }

    /**
     * Define el valor de la propiedad cantCred.
     * 
     */
    public void setCantCred(double value) {
        this.cantCred = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaReg.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaReg() {
        return fechaReg;
    }

    /**
     * Define el valor de la propiedad fechaReg.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaReg(XMLGregorianCalendar value) {
        this.fechaReg = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad imagenDir.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagenDir() {
        return imagenDir;
    }

    /**
     * Define el valor de la propiedad imagenDir.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagenDir(String value) {
        this.imagenDir = value;
    }

    /**
     * Obtiene el valor de la propiedad visitas.
     * 
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Define el valor de la propiedad visitas.
     * 
     */
    public void setVisitas(int value) {
        this.visitas = value;
    }

}
