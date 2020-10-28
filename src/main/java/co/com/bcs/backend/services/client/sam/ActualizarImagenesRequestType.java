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
 * <p>Java class for ActualizarImagenesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActualizarImagenesRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cabeceraEntrada" type="{http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0}CabeceraEntrada" minOccurs="0"/&gt;
 *         &lt;element name="numIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="canalLlamando" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="listaArchivos" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}ListaArchivosType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarImagenesRequestType", propOrder = {
    "cabeceraEntrada",
    "numIdentificacion",
    "tipoDocumento",
    "canalLlamando",
    "listaArchivos"
})
@Getter
@Setter
@NoArgsConstructor 
 public class ActualizarImagenesRequestType {

    protected CabeceraEntrada cabeceraEntrada;
    @XmlElement(required = true)
    protected String numIdentificacion;
    @XmlElement(required = true)
    protected String tipoDocumento;
    @XmlElement(required = true)
    protected String canalLlamando;
    @XmlElement(required = true)
    protected ListaArchivosType listaArchivos;

}
