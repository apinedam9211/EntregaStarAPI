//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.09 at 11:22:20 AM COT 
//


package co.com.bcs.backend.services.client.samotc; 
 import lombok.NoArgsConstructor; 

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.jayway.jsonpath.internal.function.ParamType;


/**
 * <p>Java class for Listain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Listain"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaParam" type="{http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoOTC/v1.0}in" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Listain", propOrder = {
    "listaParam"
})
@NoArgsConstructor 
 public class ListaParamType {

    protected List<ParamType> listaParam;

    /**
     * Gets the value of the listaParam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaParam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link in }
     * 
     * 
     */
    public List<ParamType> getListaParam() {
        if (listaParam == null) {
            listaParam = new ArrayList<>();
        }
        return this.listaParam;
    }

}
