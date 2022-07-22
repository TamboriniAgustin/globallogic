package global.logic.bci.test.validators;

import static org.mockito.Mockito.doNothing

import global.logic.bci.test.exceptions.WrongInputException
import global.logic.bci.test.models.Phone
import global.logic.bci.test.models.request.RegisterNewUserRequest
import spock.lang.Specification

class UsersAuthenticationValidatorSpec extends Specification {
	private UsersAuthenticationValidator validator = new UsersAuthenticationValidator();

	def "Datos de registro validos - Sin telefonos"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			notThrown(WrongInputException)
	}
	
	def "Datos de registro validos - Con telefonos"() {
		given:
			Phone phone = new Phone();
			phone.setNumber(21312312);
			phone.setCityCode(12);
			phone.setCountryCode("ARG");
			
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
			request.addPhone(phone);
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			notThrown(WrongInputException)
	}
	
	def "Datos de registro invalidos: correo electronico no recibido"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setPassword("passw0rDe4sy");
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			thrown(WrongInputException)
	}
	
	def "Datos de registro invalidos: correo electronico invalido"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundocom");
			request.setPassword("passw0rDe4sy");
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			thrown(WrongInputException)
	}
	
	def "Datos de registro invalidos: password no recibida"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundocom");
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			thrown(WrongInputException)
	}
	
	def "Datos de registro invalidos: longitud de password invalida"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundocom");
			request.setPassword("passw0rDe4syaaaaaaaaaaaaaaaaa");
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			thrown(WrongInputException)
	}
	
	def "Datos de registro invalidos: formato de password invalido"() {
		given:
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundocom");
			request.setPassword("passw0rDe4S");
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			thrown(WrongInputException)
	}
	
	def "Datos de registro validos - Con telefono sin codigo de pais"() {
		given:
			Phone phone = new Phone();
			phone.setNumber(523432423);
			phone.setCityCode(12);
			
			RegisterNewUserRequest request = new RegisterNewUserRequest();
			request.setName("Hola Mundo");
			request.setEmail("hola@mundo.com");
			request.setPassword("passw0rDe4sy");
			request.addPhone(phone);
		when:
			validator.validateNewUserRegistrationInputs(request);
		then:
			thrown(WrongInputException)
	}
}