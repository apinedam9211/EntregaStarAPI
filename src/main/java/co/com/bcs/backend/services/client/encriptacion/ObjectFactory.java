//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.09 at 04:29:13 PM COT 
//


package co.com.bcs.backend.services.client.encriptacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import lombok.NoArgsConstructor;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.bcs.apibcs.services.client.encriptacion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
@NoArgsConstructor
public class ObjectFactory {

    private static final String QNAME_CABECERA = "http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0";
    private static final QName _EncriptarMensajeAESRequest_QNAME = new QName("http://xmlns.bancocajasocial.com/co/schemas/operacion/encriptarMensajeAES/v1.0", "encriptarMensajeAESRequest");
    private static final QName _EncriptarMensajeAESResponse_QNAME = new QName("http://xmlns.bancocajasocial.com/co/schemas/operacion/encriptarMensajeAES/v1.0", "encriptarMensajeAESResponse");
    private static final QName _CabeceraRespuestaMultiple_QNAME = new QName(QNAME_CABECERA, "cabeceraRespuestaMultiple");
    private static final QName _CabeceraEntrada_QNAME = new QName(QNAME_CABECERA, "cabeceraEntrada");
    private static final QName _CabeceraSalida_QNAME = new QName(QNAME_CABECERA, "cabeceraSalida");

    /**
     * Create an instance of {@link EncriptarMensajeAESRequest }
     * 
     */
    public EncriptarMensajeAESRequest createEncriptarMensajeAESRequest() {
        return new EncriptarMensajeAESRequest();
    }

    /**
     * Create an instance of {@link EncriptarMensajeAESResponse }
     * 
     */
    public EncriptarMensajeAESResponse createEncriptarMensajeAESResponse() {
        return new EncriptarMensajeAESResponse();
    }

    /**
     * Create an instance of {@link CabeceraRespuestaMultiple }
     * 
     */
    public CabeceraRespuestaMultiple createCabeceraRespuestaMultiple() {
        return new CabeceraRespuestaMultiple();
    }

    /**
     * Create an instance of {@link CabeceraEntrada }
     * 
     */
    public CabeceraEntrada createCabeceraEntrada() {
        return new CabeceraEntrada();
    }

    /**
     * Create an instance of {@link CabeceraSalida }
     * 
     */
    public CabeceraSalida createCabeceraSalida() {
        return new CabeceraSalida();
    }

    /**
     * Create an instance of {@link Invocador }
     * 
     */
    public Invocador createInvocador() {
        return new Invocador();
    }

    /**
     * Create an instance of {@link RespuestaError }
     * 
     */
    public RespuestaError createRespuestaError() {
        return new RespuestaError();
    }

    /**
     * Create an instance of {@link DetallesRespuestas }
     * 
     */
    public DetallesRespuestas createDetallesRespuestas() {
        return new DetallesRespuestas();
    }

    /**
     * Create an instance of {@link DetalleRespuesta }
     * 
     */
    public DetalleRespuesta createDetalleRespuesta() {
        return new DetalleRespuesta();
    }

    /**
     * Create an instance of {@link Seguridad }
     * 
     */
    public Seguridad createSeguridad() {
        return new Seguridad();
    }

    /**
     * Create an instance of {@link DetalleError }
     * 
     */
    public DetalleError createDetalleError() {
        return new DetalleError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncriptarMensajeAESRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.bancocajasocial.com/co/schemas/operacion/encriptarMensajeAES/v1.0", name = "encriptarMensajeAESRequest")
    public JAXBElement<EncriptarMensajeAESRequest> createEncriptarMensajeAESRequest(EncriptarMensajeAESRequest value) {
        return new JAXBElement<>(_EncriptarMensajeAESRequest_QNAME, EncriptarMensajeAESRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncriptarMensajeAESResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.bancocajasocial.com/co/schemas/operacion/encriptarMensajeAES/v1.0", name = "encriptarMensajeAESResponse")
    public JAXBElement<EncriptarMensajeAESResponse> createEncriptarMensajeAESResponse(EncriptarMensajeAESResponse value) {
        return new JAXBElement<>(_EncriptarMensajeAESResponse_QNAME, EncriptarMensajeAESResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabeceraRespuestaMultiple }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0", name = "cabeceraRespuestaMultiple")
    public JAXBElement<CabeceraRespuestaMultiple> createCabeceraRespuestaMultiple(CabeceraRespuestaMultiple value) {
        return new JAXBElement<>(_CabeceraRespuestaMultiple_QNAME, CabeceraRespuestaMultiple.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabeceraEntrada }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0", name = "cabeceraEntrada")
    public JAXBElement<CabeceraEntrada> createCabeceraEntrada(CabeceraEntrada value) {
        return new JAXBElement<>(_CabeceraEntrada_QNAME, CabeceraEntrada.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabeceraSalida }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.bancocajasocial.com/co/comunes/schema/Cabeceras/V1.0", name = "cabeceraSalida")
    public JAXBElement<CabeceraSalida> createCabeceraSalida(CabeceraSalida value) {
        return new JAXBElement<>(_CabeceraSalida_QNAME, CabeceraSalida.class, null, value);
    }

}
