package global.logic.bci.test.services;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import global.logic.bci.test.exceptions.InsertUserException;
import global.logic.bci.test.models.ErrorCodes;
import global.logic.bci.test.models.Phone;
import global.logic.bci.test.models.User;
import global.logic.bci.test.models.User.UserBuilder;
import global.logic.bci.test.models.request.RegisterNewUserRequest;
import global.logic.bci.test.repositories.UsersAuthenticationRepository;
import global.logic.bci.test.utils.encoding.Base64Decoder;
import global.logic.bci.test.utils.encoding.Base64Encoder;
import global.logic.bci.test.utils.tokens.JwtGeneratorRSA256;

@Service
public class UsersAuthenticationService {
	private static final Logger logger = LoggerFactory.getLogger(UsersAuthenticationService.class);
	@Autowired
	private UsersAuthenticationRepository repository;
	@Autowired
	private Base64Encoder passwordEncoder;
	@Autowired
	private Base64Decoder passwordDecoder;
	@Autowired
	private JwtGeneratorRSA256 jwtGenerator;
	
	/*
	 	Se generan los datos necesarios del usuario y se da el alta en la base de datos
	 	Params: @body (recibe la información necesaria para generar un alta)
	*/
	public User registerNewUser(RegisterNewUserRequest body) throws NoSuchAlgorithmException {
		UserBuilder newUser = User.builder();
		
		//Generación del ID del usuario
		String id = generateID();
		newUser.id(id);
		logger.info("Se asigno el ID [{}] al usuario [{}]", id, body.getEmail());
		
		//Establecemos de la fecha de creación
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		newUser.created(currentTimestamp);
		
		//Seteamos la fecha actual como fecha de último login
		newUser.lastLogin(currentTimestamp);
		
		//Seteamos la cuenta como activa (como no hay definición al respecto, se asume que queda activa al registrarse y no va a cambiar)
		newUser.isActive(true);
		
		//Encripción de la password
		String encodedPassword = passwordEncoder.encodePassword(body.getPassword());
		newUser.password(encodedPassword);
		logger.debug("La password se encripto correctamente");
		
		newUser.name(body.getName());
		newUser.email(body.getEmail());
		newUser.phones(body.getPhones());
		User userInformation = newUser.build();
		
		//Generación del token
		String token = generateJWT(userInformation);
		logger.info("Se genero el token de usuario [{}] correctamente", token);
		userInformation.setToken(token);
		
		//Registro del usuario en la base de datos
		try {
			repository.insertUser(userInformation);
		} catch(DuplicateKeyException e) {
			throw new InsertUserException("[Sign-Up] El usuario ingresado ya fue registrado previamente", ErrorCodes.UA0004);
		}
		
		//Si el usuario fue registrado correctamente, registramos sus números de teléfono
		try {
			Optional.ofNullable(userInformation.getPhones()).ifPresent(phones -> {
				phones.forEach(phone -> {
					repository.insertPhone(userInformation.getId(), phone);
				});
			}); 
		} catch(DuplicateKeyException e) {
			//Si algo falla hacemos rollback del usuario creado
			repository.deleteUser(userInformation.getId());
			throw new InsertUserException("[Sign-Up] Uno de los telefonos ingresados se encuentra repetido", ErrorCodes.UA0005);
		} catch(Exception e) {
			//Si algo falla hacemos rollback del usuario creado
			repository.deleteUser(userInformation.getId());
			throw e;
		}
		
		return userInformation;
	}
	
	/*
	 	Se consulta la base de datos para corroborar la información del usuario
	 	Params: @token
	*/
	public User login(String token) throws NoSuchAlgorithmException {
		//Realizamos la consulta del usuario buscando por token
		User user = repository.selectUserByToken(token);
		//Si se encontró el usuario, buscamos sus teléfonos
		List<Phone> userPhones = repository.selectPhonesByUserId(user.getId());
		user.setPhones(userPhones);
		
		//Si se obtuvo el usuario sin ningún problema hay que actualizar el token y el último ingreso
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		user.setLastLogin(currentTimestamp);
		
		String newToken = generateJWT(user);
		logger.info("Se genero el nuevo token de usuario [{}] correctamente", token);
		user.setToken(newToken);
		
		//Actualizamos los datos del usuario en la base de datos
		repository.updateUserByNewLogin(user);
		logger.info("Nuevo inicio de sesion registrado");
		
		//Desencriptamos la password ya que el ejercicio requiere verla como se ingresó
		String decodedPassword = passwordDecoder.decodePassword(user.getPassword());
		user.setPassword(decodedPassword);
		
		return user;
	}
	
	/*
	 	Obtención de IDs
	*/
	private String generateID() {
		return UUID.randomUUID().toString();
	}
	
	/*
	 	Generación de Json Web Token para el usuario
	*/
	private String generateJWT(User userInformation) throws NoSuchAlgorithmException {
		Map<String, String> jwtClaims = new HashMap<>();
		
		jwtClaims.put("user_id", userInformation.getId());
		jwtClaims.put("user_name", userInformation.getName());
		jwtClaims.put("created_at", userInformation.getCreated().toString());
		
		return jwtGenerator.generateJwtToken(jwtClaims);
	}
}