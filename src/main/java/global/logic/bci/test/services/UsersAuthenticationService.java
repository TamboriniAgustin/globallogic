package global.logic.bci.test.services;

import java.sql.Timestamp;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.logic.bci.test.models.NewUser;
import global.logic.bci.test.models.NewUser.NewUserBuilder;
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
	public NewUser registerNewUser(RegisterNewUserRequest body) {
		NewUserBuilder newUser = NewUser.builder();
		
		//Generación del ID del usuario
		String id = generateID();
		newUser.id(id);
		logger.info("Se asigno el ID [{}] al usuario [{}]", id, body.getEmail());
		
		//Establecemos de la fecha de creación
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		newUser.created(currentTimestamp);
		
		//Seteamos la fecha actual como fecha de último login
		newUser.lastLogin(currentTimestamp);
		
		//TODO: Generar token JWT
		
		//Seteamos la cuenta como activa (como no hay definición al respecto, se asume que queda activa al registrarse y no va a cambiar)
		newUser.isActive(true);
		
		//TODO: Encriptar password
		//TODO: Implementar consulta de la base de datos
		
		return newUser.build();
	}
	
	/*
	 	Se consulta la base de datos para corroborar la información del usuario
	 	Params: @token
	*/
	public void login(String token) {
		
	}
	
	/*
	 	Obtención de IDs
	*/
	private String generateID() {
		return UUID.randomUUID().toString();
	}
}