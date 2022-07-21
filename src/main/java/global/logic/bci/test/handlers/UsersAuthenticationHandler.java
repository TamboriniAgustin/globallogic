package global.logic.bci.test.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import global.logic.bci.test.exceptions.WrongInputException;
import global.logic.bci.test.models.ErrorCodes;
import global.logic.bci.test.models.NewUser;
import global.logic.bci.test.models.request.RegisterNewUserRequest;
import global.logic.bci.test.models.response.LoginResponse;
import global.logic.bci.test.models.response.RegisterNewUserResponse;
import global.logic.bci.test.services.UsersAuthenticationService;
import global.logic.bci.test.utils.ErrorUtils;
import global.logic.bci.test.validators.UsersAuthenticationValidator;

@Component
public class UsersAuthenticationHandler {
	private static final Logger logger = LoggerFactory.getLogger(UsersAuthenticationHandler.class);
	@Autowired
	private UsersAuthenticationValidator validator;
	@Autowired
	private UsersAuthenticationService service;
	
	/*
	 	Se realizan las operaciones necesarias para crear el usuario y se maneja la respuesta recibida
	 	Params: @body (recibe la información necesaria para generar un alta)
	*/
	public ResponseEntity<RegisterNewUserResponse> registerNewUser(RegisterNewUserRequest body) {
		RegisterNewUserResponse response = new RegisterNewUserResponse();
		
		try {
			validator.validateNewUserRegistrationInputs(body);
			logger.debug("[Sign-Up] Datos de entrada validados con exito");
			
			NewUser newUserInformation = service.registerNewUser(body);
			logger.info("[Sign-Up] El usuario [{}] se dio de alta con exito en el sistema", newUserInformation.getId());
			
			response.setId(newUserInformation.getId());
			response.setToken(newUserInformation.getToken());
			response.setCreated(newUserInformation.getCreated().toString());
			response.setLastLogin(newUserInformation.getLastLogin().toString());
			response.setActive(newUserInformation.isActive());
			
			return new ResponseEntity<RegisterNewUserResponse>(response, HttpStatus.OK);
		} catch(WrongInputException e) {
			logger.error("[Sign-Up] Ocurrio un error con alguno de los datos de entrada");
			response.setErrors(e.getErrorsFounded());
			return new ResponseEntity<RegisterNewUserResponse>(response, HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			logger.error("[Sign-Up] Ocurrio un error inesperado");
			response.addError(ErrorUtils.generateError(ErrorCodes.UA0000));
			return new ResponseEntity<RegisterNewUserResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 	Se realizan las operaciones de login y se maneja la respuesta recibida
	 	Params: @token
	*/
	public ResponseEntity<LoginResponse> login(String token) {
		LoginResponse response = new LoginResponse();
		
		try {
			service.login(token);
			logger.info("[Login] El usuario [{}] ingreso al sistema correctamente", "test@temporal.com");
			
			return new ResponseEntity<LoginResponse>(HttpStatus.OK);
		} catch(Exception e) {
			logger.error("[Login] Ocurrio un error inesperado");
			response.addError(ErrorUtils.generateError(ErrorCodes.UA0000));
			return new ResponseEntity<LoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}