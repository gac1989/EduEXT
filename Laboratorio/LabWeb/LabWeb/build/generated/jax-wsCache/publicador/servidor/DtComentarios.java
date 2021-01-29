
package servidor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtComentarios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtComentarios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datosEst" type="{http://PRESENTACION/}dtUsuario" minOccurs="0"/>
 *         &lt;element name="respuestas" type="{http://PRESENTACION/}dtComentarios" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtComentarios", propOrder = {
    "id",
    "comentario",
    "datosEst",
    "respuestas"
})
public class DtComentarios {

    protected Long id;
    protected String comentario;
    protected DtUsuario datosEst;
    @XmlElement(nillable = true)
    protected List<DtComentarios> respuestas;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad comentario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Define el valor de la propiedad comentario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

    /**
     * Obtiene el valor de la propiedad datosEst.
     * 
     * @return
     *     possible object is
     *     {@link DtUsuario }
     *     
     */
    public DtUsuario getDatosEst() {
        return datosEst;
    }

    /**
     * Define el valor de la propiedad datosEst.
     * 
     * @param value
     *     allowed object is
     *     {@link DtUsuario }
     *     
     */
    public void setDatosEst(DtUsuario value) {
        this.datosEst = value;
    }

    /**
     * Gets the value of the respuestas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtComentarios }
     * 
     * 
     */
    public List<DtComentarios> getRespuestas() {
        if (respuestas == null) {
            respuestas = new ArrayList<DtComentarios>();
        }
        return this.respuestas;
    }

}
