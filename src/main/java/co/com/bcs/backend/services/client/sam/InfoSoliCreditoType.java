//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.02 at 12:34:30 PM COT 
//


package co.com.bcs.backend.services.client.sam; 
 import lombok.NoArgsConstructor; 

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;


/**
 * <p>Java class for InfoSoliCreditoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InfoSoliCreditoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numSolicitud" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="celular" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="identificacionPromotor" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}IdentificacionType"/&gt;
 *         &lt;element name="montoSolic" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="plazo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ubicacion" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoUbicacionType"/&gt;
 *         &lt;element name="personal" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoPersonalType"/&gt;
 *         &lt;element name="conyuge" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoConyugeType"/&gt;
 *         &lt;element name="actividad" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoActividadEconomicaType"/&gt;
 *         &lt;element name="infoIngresos" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoIngresosType"/&gt;
 *         &lt;element name="infoFamiliar" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoFamiliarType"/&gt;
 *         &lt;element name="infoSegSocial" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoPersona/v1.0}InfoSegSocialType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoSoliCreditoType", propOrder = {
    "numSolicitud",
    "celular",
    "identificacionPromotor",
    "montoSolic",
    "plazo",
    "ubicacion",
    "personal",
    "conyuge",
    "actividad",
    "infoIngresos",
    "infoFamiliar",
    "infoSegSocial"
})
@Getter
@Setter
@NoArgsConstructor 
 public class InfoSoliCreditoType {

    @XmlElement(required = true)
    protected String numSolicitud;
    @XmlElement(required = true)
    protected String celular;
    @XmlElement(required = true)
    protected IdentificacionType identificacionPromotor;
    @XmlElement(required = true)
    protected BigDecimal montoSolic;
    @XmlElement(required = true)
    protected BigInteger plazo;
    @XmlElement(required = true)
    protected InfoUbicacionType ubicacion;
    @XmlElement(required = true)
    protected InfoPersonalType personal;
    @XmlElement(required = true)
    protected InfoConyugeType conyuge;
    @XmlElement(required = true)
    protected InfoActividadEconomicaType actividad;
    @XmlElement(required = true)
    protected InfoIngresosType infoIngresos;
    @XmlElement(required = true)
    protected InfoFamiliarType infoFamiliar;
    @XmlElement(required = true)
    protected InfoSegSocialType infoSegSocial;
}