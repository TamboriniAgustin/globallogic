package global.logic.bci.test.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import global.logic.bci.test.exceptions.WrongInputException;
import global.logic.bci.test.models.request.RegisterNewUserRequest;
import global.logic.bci.test.utils.ErrorUtils;
import global.logic.bci.test.utils.RegularExpressionsUtils;
import global.logic.bci.test.models.Error;
import global.logic.bci.test.models.ErrorCodes;

@Component
public class UsersAuthenticationValidator {
	/*
		Validaci�n de la informaci�n ingresada para dar de alta un usuario
	 	Params: @body
	*/
	public void validateNewUserRegistrationInputs(RegisterNewUserRequest body) {
		List<Error> errors = new ArrayList<>();
		
		//Validaci�n de los datos de entrada
		if(!RegularExpressionsUtils.match(RegularExpressionsUtils.EMAIL_ADDRESS_REGEXP, Optional.ofNullable(body.getEmail()).orElse(""))) {
			errors.add(ErrorUtils.generateError(ErrorCodes.UA0001));
		}
		validatePassword(body, errors);
		
		
		//Si se encontr� alg�n error previamente genero la excepci�n correspondiente para que el handler pueda manejarla
		if(!errors.isEmpty()) {
			throw new WrongInputException(errors);
		}
	}
	
	/*
		Validaci�n del campo password
	*/
	private void validatePassword(RegisterNewUserRequest body, List<Error> errors) {
		if(Optional.ofNullable(body.getPassword()).orElse("").length() < 8 || Optional.ofNullable(body.getPassword()).orElse("").length() > 12) {
			errors.add(ErrorUtils.generateError(ErrorCodes.UA0002));
		}
		if(!RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, Optional.ofNullable(body.getPassword()).orElse(""))) {
			errors.add(ErrorUtils.generateError(ErrorCodes.UA0003));
		}
	}
}