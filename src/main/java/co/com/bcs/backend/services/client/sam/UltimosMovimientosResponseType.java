//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.02 at 12:34:30 PM COT 
//


package co.com.bcs.backend.services.client.sam; 
 import lombok.NoArgsConstructor; 

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;


/**
 * <p>Java class for UltimosMovimientosResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UltimosMovimientosResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cabeceraSalida" type="{http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0}CabeceraSalida" minOccurs="0"/&gt;
 *         &lt;element name="movimientos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="producto" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}ProductoComunType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UltimosMovimientosResponseType", propOrder = {
    "cabeceraSalida",
    "movimientos",
    "producto"
})
@Getter
@Setter
@NoArgsConstructor 
 public class UltimosMovimientosResponseType {

    protected CabeceraSalida cabeceraSalida;
    protected String movimientos;
    protected ProductoComunType producto;
}
