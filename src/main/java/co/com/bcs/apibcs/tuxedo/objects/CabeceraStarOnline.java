package co.com.bcs.apibcs.tuxedo.objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class CabeceraStarOnline {

    private String countryCode;
    private String networkId;
    private String canal;
    private String subcanal;
    private String idTerminal;
    private String usuario;
    private String ipCliente;
    private String macCliente;
    private String ipServer;
    private String macServer;
    private String llaveSesion;
    private String nombreServicio;


    public String toTrama()
    {

     return   StringUtils.leftPad(StringUtils.defaultString(countryCode) , 3)+
        StringUtils.rightPad(StringUtils.defaultString(networkId) , 4)+
        StringUtils.leftPad(StringUtils.defaultString(canal) , 3)+
        StringUtils.leftPad(StringUtils.defaultString(subcanal) , 2)+
        StringUtils.leftPad(StringUtils.defaultString(idTerminal) , 20)+
        StringUtils.leftPad(StringUtils.defaultString(usuario) , 20)+
        StringUtils.leftPad(StringUtils.defaultString(ipCliente) , 39)+
        StringUtils.leftPad(StringUtils.defaultString(macCliente) , 17)+
        StringUtils.leftPad(StringUtils.defaultString(ipServer) , 39)+
        StringUtils.leftPad(StringUtils.defaultString(macServer) , 17)+
        StringUtils.leftPad(StringUtils.defaultString(llaveSesion) , 16)+
        StringUtils.leftPad(StringUtils.defaultString(nombreServicio) , 30 );
    }
}
