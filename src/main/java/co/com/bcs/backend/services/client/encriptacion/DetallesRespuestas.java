//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.09 at 04:29:13 PM COT 
//


package co.com.bcs.backend.services.client.encriptacion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.NoArgsConstructor;


/**
 * <p>Java class for DetallesRespuestas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetallesRespuestas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="detalleRespuesta" type="{http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0}DetalleRespuesta" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetallesRespuestas", propOrder = {
    "detalleRespuesta"
})
@NoArgsConstructor
public class DetallesRespuestas {

    protected List<DetalleRespuesta> detalleRespuesta;

    /**
     * Gets the value of the detalleRespuesta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleRespuesta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleRespuesta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleRespuesta }
     * 
     * 
     */
    public List<DetalleRespuesta> getDetalleRespuesta() {
        if (detalleRespuesta == null) {
            detalleRespuesta = new ArrayList<>();
        }
        return this.detalleRespuesta;
    }

}
