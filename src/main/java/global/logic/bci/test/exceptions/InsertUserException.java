package global.logic.bci.test.exceptions;

import global.logic.bci.test.models.ErrorCodes;
import lombok.Getter;

@Getter
public class InsertUserException extends RuntimeException {
	private static final long serialVersionUID = 7287586244043534546L;
	private ErrorCodes errorCode;
	
	public InsertUserException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}