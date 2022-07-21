package global.logic.bci.test.models;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public enum ErrorCodes {
	UA0000(0, "Ha ocurrido un error inesperado", new Timestamp(System.currentTimeMillis())),
	UA0001(1, "El correo electronico no cumple el formato especificado (aaaaaaaa@correo.dominio)", new Timestamp(System.currentTimeMillis())),
	UA0002(2, "La password debe tener una longitud de entre 8 y 12 caracteres", new Timestamp(System.currentTimeMillis())),
	UA0003(3, "La password debe contener una mayuscula y dos caracteres numericos", new Timestamp(System.currentTimeMillis()));
	
	private int code;
	private String reason;
	private Timestamp timestamp;
	
	private ErrorCodes(int code, String reason, Timestamp timestamp) {
		this.code = code;
		this.reason = reason;
		this.timestamp = timestamp;
	}
}