package co.com.bcs.apibcs.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "API_BCS_ALIADO")
@Data
public class ApiBCSAliado {

	@Id
	@Column(name = "NOMBRE_ALIADO")
	private String nombreAliado;
	
	@Column(name = "NIT_ALIADO")
	private String nitAliado;

	@Column(name = "CODIGO_SITIO")
	private String codigoSitio;

	@Column(name = "PUNTO_SERVICIO")
	private String puntoServicio;

	@Column(name = "CODIGO_TERMINAL")
	private String codigoTerminal;

	@Column(name = "CODIGO_CORRESPONSAL")
	private String codigoCorresponsal;

	@Column(name = "GRUPO")
	private String grupo;
}
