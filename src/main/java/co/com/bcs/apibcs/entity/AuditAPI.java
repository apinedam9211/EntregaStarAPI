package co.com.bcs.apibcs.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import java.sql.Timestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "API_BCS_AUDIT")
public class AuditAPI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "PRINCIPAL_NAME")
	private String principal_name;

	@Column(name = "REQUEST")
	@Lob
	private String request;

	@Column(name = "RESPONSE")
	@Lob
	private String response;

	@Column(name = "IP")
	private String ip;

	@Column(name = "ERROR_MESSAGE")
	@Lob
	private String error_message;

	@Column(name = "INIT_TIME")
	private Timestamp init_time;

	@Column(name = "FINAL_TIME")
	private Timestamp final_time;

	@Column(name = "TRANSACCION")
	private String transaccion;

	@Column(name = "HTTP_METHOD")
	private String httpMethod;

	@Column(name = "RESULT")
	private Character result;

}
