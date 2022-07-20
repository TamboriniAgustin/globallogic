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
		Validación de la información ingresada para dar de alta un usuario
	 	Params: @body
	*/
	public void validateNewUserRegistrationInputs(RegisterNewUserRequest body) {
		List<Error> errors = new ArrayList<>();
		
		//Validación de los datos de entrada
		
		//Si se encontró algún error previamente genero la excepción correspondiente para que el handler pueda manejarla
		if(!errors.isEmpty()) {
			throw new WrongInputException(errors);
		}
	}
}