//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.09 at 11:22:20 AM COT 
//


package co.com.bcs.backend.services.client.samotc; 
 import lombok.NoArgsConstructor; 

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;


/**
 * <p>Java class for DatosRetiroType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosRetiroType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="celular" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="valorRetiro" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="medioRetiro" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosRetiroType", propOrder = {
    "celular",
    "valorRetiro",
    "medioRetiro"
})
@Getter
@Setter
@NoArgsConstructor 
 public class DatosRetiroType {

    @XmlElement(required = true)
    protected String celular;
    @XmlElement(required = true)
    protected BigDecimal valorRetiro;
    @XmlElement(required = true)
    protected String medioRetiro;

    
}
