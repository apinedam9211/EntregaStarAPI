package co.com.bcs.apibcs.services;

import co.com.bcs.apibcs.services.dto.OTCRetiroRequest;

public interface OTCTokenService {

	String solicitarToken(OTCRetiroRequest request);
    
}