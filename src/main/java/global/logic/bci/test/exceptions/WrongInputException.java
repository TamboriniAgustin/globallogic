package global.logic.bci.test.exceptions;

import java.util.List;

import global.logic.bci.test.models.Error;
import lombok.Getter;

@Getter
public class WrongInputException extends RuntimeException {
	private static final long serialVersionUID = -6074290393524151484L;
	private List<Error> errorsFounded;
	
	public WrongInputException(List<Error> errorsFounded) {
		this.errorsFounded = errorsFounded;
	}
}