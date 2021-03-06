//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.09 at 11:22:20 AM COT 
//


package co.com.bcs.backend.services.client.samotc; 
 import lombok.NoArgsConstructor; 

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.bcs.apibanco.services.client.samotc package. 
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
    private static final String QNAME_INFO_OTC = "http://xmlns.bancocajasocial.com/co/schemas/operacion/sam/infoOTC/v1.0";
    private static final QName _SolicitarOTCRequest_QNAME = new QName(QNAME_INFO_OTC, "solicitarOTCRequest");
    private static final QName _SolicitarOTCResponse_QNAME = new QName(QNAME_INFO_OTC, "solicitarOTCResponse");
    private static final QName _ValidarOTCRequest_QNAME = new QName(QNAME_INFO_OTC, "validarOTCRequest");
    private static final QName _ValidarOTCResponse_QNAME = new QName(QNAME_INFO_OTC, "validarOTCResponse");
    private static final QName _SolicitarOTCRetiroRequest_QNAME = new QName(QNAME_INFO_OTC, "solicitarOTCRetiroRequest");
    private static final QName _SolicitarOTCRetiroResponse_QNAME = new QName(QNAME_INFO_OTC, "solicitarOTCRetiroResponse");
    private static final QName _CabeceraSalida_QNAME = new QName(QNAME_CABECERA, "cabeceraSalida");
    private static final QName _CabeceraEntrada_QNAME = new QName(QNAME_CABECERA, "cabeceraEntrada");
    private static final QName _CabeceraRespuestaMultiple_QNAME = new QName(QNAME_CABECERA, "cabeceraRespuestaMultiple");


    /**
     * Create an instance of {@link SolicitarOTCRequestType }
     * 
     */
    public SolicitarOTCRequestType createSolicitarOTCRequestType() {
        return new SolicitarOTCRequestType();
    }

    /**
     * Create an instance of {@link SolicitarOTCResponseType }
     * 
     */
    public SolicitarOTCResponseType createSolicitarOTCResponseType() {
        return new SolicitarOTCResponseType();
    }

    /**
     * Create an instance of {@link ValidarOTCRequestType }
     * 
     */
    public ValidarOTCRequestType createValidarOTCRequestType() {
        return new ValidarOTCRequestType();
    }

    /**
     * Create an instance of {@link ValidarOTCResponseType }
     * 
     */
    public ValidarOTCResponseType createValidarOTCResponseType() {
        return new ValidarOTCResponseType();
    }

    /**
     * Create an instance of {@link SolicitarOTCRetiroRequestType }
     * 
     */
    public SolicitarOTCRetiroRequestType createSolicitarOTCRetiroRequestType() {
        return new SolicitarOTCRetiroRequestType();
    }

    /**
     * Create an instance of {@link SolicitarOTCRetiroResponseType }
     * 
     */
    public SolicitarOTCRetiroResponseType createSolicitarOTCRetiroResponseType() {
        return new SolicitarOTCRetiroResponseType();
    }

    /**
     * Create an instance of {@link DatosRetiroType }
     * 
     */
    public DatosRetiroType createDatosRetiroType() {
        return new DatosRetiroType();
    }

    /**
     * Create an instance of {@link in }
     * 
     */
    public ParamType createParamType() {
        return new ParamType();
    }

    /**
     * Create an instance of {@link Listain }
     * 
     */
    public ListaParamType createListaParamType() {
        return new ListaParamType();
    }

    /**
     * Create an instance of {@link IdentificacionType }
     * 
     */
    public IdentificacionType createIdentificacionType() {
        return new IdentificacionType();
    }

    /**
     * Create an instance of {@link CabeceraSalida }
     * 
     */
    public CabeceraSalida createCabeceraSalida() {
        return new CabeceraSalida();
    }

    /**
     * Create an instance of {@link CabeceraEntrada }
     * 
     */
    public CabeceraEntrada createCabeceraEntrada() {
        return new CabeceraEntrada();
    }

    /**
     * Create an instance of {@link CabeceraRespuestaMultiple }
     * 
     */
    public CabeceraRespuestaMultiple createCabeceraRespuestaMultiple() {
        return new CabeceraRespuestaMultiple();
    }

    /**
     * Create an instance of {@link DetalleError }
     * 
     */
    public DetalleError createDetalleError() {
        return new DetalleError();
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
     * Create an instance of {@link Seguridad }
     * 
     */
    public Seguridad createSeguridad() {
        return new Seguridad();
    }

    /**
     * Create an instance of {@link DetalleRespuesta }
     * 
     */
    public DetalleRespuesta createDetalleRespuesta() {
        return new DetalleRespuesta();
    }

    /**
     * Create an instance of {@link DetallesRespuestas }
     * 
     */
    public DetallesRespuestas createDetallesRespuestas() {
        return new DetallesRespuestas();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitarOTCRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_INFO_OTC, name = "solicitarOTCRequest")
    public JAXBElement<SolicitarOTCRequestType> createSolicitarOTCRequest(SolicitarOTCRequestType value) {
        return new JAXBElement<>(_SolicitarOTCRequest_QNAME, SolicitarOTCRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitarOTCResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_INFO_OTC, name = "solicitarOTCResponse")
    public JAXBElement<SolicitarOTCResponseType> createSolicitarOTCResponse(SolicitarOTCResponseType value) {
        return new JAXBElement<>(_SolicitarOTCResponse_QNAME, SolicitarOTCResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarOTCRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_INFO_OTC, name = "validarOTCRequest")
    public JAXBElement<ValidarOTCRequestType> createValidarOTCRequest(ValidarOTCRequestType value) {
        return new JAXBElement<>(_ValidarOTCRequest_QNAME, ValidarOTCRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarOTCResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_INFO_OTC, name = "validarOTCResponse")
    public JAXBElement<ValidarOTCResponseType> createValidarOTCResponse(ValidarOTCResponseType value) {
        return new JAXBElement<>(_ValidarOTCResponse_QNAME, ValidarOTCResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitarOTCRetiroRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_INFO_OTC, name = "solicitarOTCRetiroRequest")
    public JAXBElement<SolicitarOTCRetiroRequestType> createSolicitarOTCRetiroRequest(SolicitarOTCRetiroRequestType value) {
        return new JAXBElement<>(_SolicitarOTCRetiroRequest_QNAME, SolicitarOTCRetiroRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitarOTCRetiroResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_INFO_OTC, name = "solicitarOTCRetiroResponse")
    public JAXBElement<SolicitarOTCRetiroResponseType> createSolicitarOTCRetiroResponse(SolicitarOTCRetiroResponseType value) {
        return new JAXBElement<>(_SolicitarOTCRetiroResponse_QNAME, SolicitarOTCRetiroResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabeceraSalida }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_CABECERA, name = "cabeceraSalida")
    public JAXBElement<CabeceraSalida> createCabeceraSalida(CabeceraSalida value) {
        return new JAXBElement<>(_CabeceraSalida_QNAME, CabeceraSalida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabeceraEntrada }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_CABECERA, name = "cabeceraEntrada")
    public JAXBElement<CabeceraEntrada> createCabeceraEntrada(CabeceraEntrada value) {
        return new JAXBElement<>(_CabeceraEntrada_QNAME, CabeceraEntrada.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CabeceraRespuestaMultiple }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = QNAME_CABECERA, name = "cabeceraRespuestaMultiple")
    public JAXBElement<CabeceraRespuestaMultiple> createCabeceraRespuestaMultiple(CabeceraRespuestaMultiple value) {
        return new JAXBElement<>(_CabeceraRespuestaMultiple_QNAME, CabeceraRespuestaMultiple.class, null, value);
    }

}
