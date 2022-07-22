package global.logic.bci.test.services;

import static org.hamcrest.CoreMatchers.any
import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyMap
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.doNothing
import static org.mockito.Mockito.doThrow
import static org.mockito.Mockito.when

import java.security.NoSuchAlgorithmException

import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.util.ReflectionTestUtils

import global.logic.bci.test.exceptions.InsertUserException
import global.logic.bci.test.models.User;
import global.logic.bci.test.models.User.UserBuilder;
import global.logic.bci.test.models.Phone
import global.logic.bci.test.models.request.RegisterNewUserRequest
import global.logic.bci.test.repositories.UsersAuthenticationRepository
import global.logic.bci.test.utils.encoding.Base64Decoder
import global.logic.bci.test.utils.encoding.Base64Encoder
import global.logic.bci.test.utils.tokens.JwtGeneratorRSA256
import spock.lang.Specification

class UsersAuthenticationServiceSpec extends Specification {
	private UsersAuthenticationService service;
	//Mocks
	private UsersAuthenticationRepository repository;
	private Base64Encoder passwordEncoder;
	private Base64Decoder passwordDecoder;
	private JwtGeneratorRSA256 jwtGenerator;
	
	public void setup() {
		service = new UsersAuthenticationService()
		
		repository = Stub(UsersAuthenticationRepository)
		passwordEncoder = Spy(Base64Encoder)
		passwordDecoder = Spy(Base64Decoder)
		jwtGenerator = Spy(JwtGeneratorRSA256)
		
		ReflectionTestUtils.setField(service, "passwordEncoder", passwordEncoder)
		ReflectionTestUtils.setField(service, "passwordDecoder", passwordDecoder)
		ReflectionTestUtils.setField(service, "jwtGenerator", jwtGenerator)
		ReflectionTestUtils.setField(service, "repository", repository)
	}
	
	/* Registro de usuario */
	public void "Registro de un usuario sin numeros de telefono correctamente"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
		when:
			def result = service.registerNewUser(request)
		then:
			notThrown(Exception)
	}
	
	public void "Registro de un usuario con 2 numeros de telefono correctamente"() {
		given:
			Phone phone1 = new Phone();
			phone1.setNumber(1111);
			phone1.setCityCode(1);
			phone1.setCountryCode("ARG");
			
			Phone phone2 = new Phone();
			phone2.setNumber(2222);
			phone2.setCityCode(42);
			phone2.setCountryCode("US");
			
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
			request.addPhone(phone1);
			request.addPhone(phone2);
		when:
			def result = service.registerNewUser(request)
		then:
			notThrown(Exception)
	}
	
	public void "Registro invalido: usuario duplicado"() {
		given:
			repository.insertUser(_ as User) >> { throw new DuplicateKeyException("Prueba") }
			
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
		when:
			service.registerNewUser(request)
		then:
			thrown(InsertUserException)
	}
	
	public void "Registro invalido: telefono duplicado"() {
		given:
			repository.insertPhone(_ as String, _ as Phone) >> { throw new DuplicateKeyException("Prueba") }
			
			Phone phone1 = new Phone();
			phone1.setNumber(1111);
			phone1.setCityCode(1);
			phone1.setCountryCode("ARG");
			
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
			request.addPhone(phone1);
		when:
			service.registerNewUser(request)
		then:
			thrown(InsertUserException)
	}
	
	/* Login de usuario */
	public void "Logueo exitoso de usuario"() {
		given:
			String token = "SADSFASOFO??20321'S==="
		when:
			def result = service.login(token)
		then:
			notThrown(Exception)
	}
	
	public void "Logueo invalido: token incorrecto"() {
		given:
			repository.selectUserByToken("abc") >> { throw new EmptyResultDataAccessException(500) }
		when:
			service.login("abc")
		then:
			thrown(EmptyResultDataAccessException)
	}
}