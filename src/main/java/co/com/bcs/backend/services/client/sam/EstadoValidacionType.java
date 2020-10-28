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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;


/**
 * <p>Java class for EstadoValidacionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstadoValidacionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="validaListReserva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="validaSolCredCurso" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="validaDocumentos" type="{http://xmlns.bancocajasocial.com/co/schemas/sam/cliente/servicioSAMCliente/v1.0}ValidaDocumentosType"/&gt;
 *         &lt;element name="validaVerifimeVig" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="vigenciaPsicometrico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="vigenciaDigital" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoValidacionType", propOrder = {
    "validaListReserva",
    "validaSolCredCurso",
    "validaDocumentos",
    "validaVerifimeVig",
    "vigenciaPsicometrico",
    "vigenciaDigital"
})
@Getter
@Setter
@NoArgsConstructor 
 public class EstadoValidacionType {

    @XmlElement(required = true)
    protected String validaListReserva;
    @XmlElement(required = true)
    protected String validaSolCredCurso;
    @XmlElement(required = true)
    protected ValidaDocumentosType validaDocumentos;
    @XmlElement(required = true)
    protected String validaVerifimeVig;
    @XmlElement(required = true)
    protected String vigenciaPsicometrico;
    @XmlElement(required = true)
    protected String vigenciaDigital;

  
}