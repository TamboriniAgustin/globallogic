package global.logic.bci.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.logic.bci.test.models.request.RegisterNewUserRequest;
import global.logic.bci.test.repositories.UsersAuthenticationRepository;

@Service
public class UsersAuthenticationService {
	private static final Logger logger = LoggerFactory.getLogger(UsersAuthenticationService.class);
	@Autowired
	private UsersAuthenticationRepository repository;
	
	/*
	 	Se generan los datos necesarios del usuario y se da el alta en la base de datos
	 	Params: @body (recibe la información necesaria para generar un alta)
	*/
	public void registerNewUser(RegisterNewUserRequest body) {
		
	}
	
	/*
	 	Se consulta la base de datos para corroborar la información del usuario
	 	Params: @token
	*/
	public void login(String token) {
		
	}
}