package co.com.bcs.apibcs.tuxedo.objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class CabeceraStarOnlineSalida {

    private String errorCode;
    
    private String descriptionError;

    private String errorType;
   
    private String status; 

    public boolean isError() {
        return ("ER".equals(this.getStatus()));
    }

    public CabeceraStarOnlineSalida(){};

    public CabeceraStarOnlineSalida(String string){

        if (StringUtils.isEmpty(string)) {
            this.setStatus("ER");
            this.setErrorCode("0002");
            this.setDescriptionError("Error en Tuxedo, mensaje Vacio");
            this.setErrorType("VAL");
            return;
        }

        if ("OK".equals(StringUtils.substring(string, 0, 2))) {
            this.setStatus("OK");
        }
        else{
            this.setStatus("ER");
            this.setErrorType(StringUtils.substring(string, 2, 5));
            this.setErrorCode(StringUtils.substring(string, 5, 10));
            this.setDescriptionError(StringUtils.substring(string, 10, 70));
        }

        
     


    }

}
