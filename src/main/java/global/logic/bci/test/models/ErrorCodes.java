package global.logic.bci.test.models;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public enum ErrorCodes {
	UA0000(0, "Ha ocurrido un error inesperado", new Timestamp(System.currentTimeMillis())),
	UA0001(1, "El correo electronico no cumple el formato especificado (aaaaaaaa@correo.dominio)", new Timestamp(System.currentTimeMillis())),
	UA0002(2, "La password debe tener una longitud de entre 8 y 12 caracteres", new Timestamp(System.currentTimeMillis())),
	UA0003(3, "La password debe contener una mayuscula y dos caracteres numericos", new Timestamp(System.currentTimeMillis())),
	UA0004(4, "El usuario ingresado ya existe, ingresa otra direccion de correo electronico", new Timestamp(System.currentTimeMillis())),
	UA0005(5, "Un telefono se ha ingresado dos veces", new Timestamp(System.currentTimeMillis())),
	UA0006(6, "Se ha ingresado un telefono sin el codigo de pais correspondiente", new Timestamp(System.currentTimeMillis())),
	UA0007(7, "El usuario ingresado es inexistente", new Timestamp(System.currentTimeMillis()));
	
	private int code;
	private String reason;
	private Timestamp timestamp;
	
	private ErrorCodes(int code, String reason, Timestamp timestamp) {
		this.code = code;
		this.reason = reason;
		this.timestamp = timestamp;
	}
}