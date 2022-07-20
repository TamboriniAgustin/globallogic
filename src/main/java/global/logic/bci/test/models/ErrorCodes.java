package global.logic.bci.test.models;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public enum ErrorCodes {
	UA0000(0, "Ha ocurrido un error inesperado", new Timestamp(System.currentTimeMillis()));
	
	private int code;
	private String reason;
	private Timestamp timestamp;
	
	private ErrorCodes(int code, String reason, Timestamp timestamp) {
		this.code = code;
		this.reason = reason;
		this.timestamp = timestamp;
	}
}