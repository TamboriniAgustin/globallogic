package global.logic.bci.test.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import global.logic.bci.test.exceptions.WrongInputException;
import global.logic.bci.test.models.request.RegisterNewUserRequest;
import global.logic.bci.test.models.Error;

@Component
public class UsersAuthenticationValidator {
	/*
		Validaci�n de la informaci�n ingresada para dar de alta un usuario
	 	Params: @body
	*/
	public void validateNewUserRegistrationInputs(RegisterNewUserRequest body) {
		List<Error> errors = new ArrayList<>();
		
		//Validaci�n de los datos de entrada
		
		//Si se encontr� alg�n error previamente genero la excepci�n correspondiente para que el handler pueda manejarla
		if(!errors.isEmpty()) {
			throw new WrongInputException(errors);
		}
	}
}