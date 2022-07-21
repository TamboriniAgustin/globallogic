package global.logic.bci.test.utils;

import spock.lang.Specification

class RegularExpressionsUtilsSpec extends Specification {
	/* EMAIL REGEXP*/
	def "Email valido: gmail"() {
		when:
			def email = "emaildeprueba@gmail.com"
		then:
			RegularExpressionsUtils.match(RegularExpressionsUtils.EMAIL_ADDRESS_REGEXP, email);
	}
	
	def "Email valido: dominio propio"() {
		when:
			def email = "emaildeprueba@soporte.empresa.com"
		then:
			RegularExpressionsUtils.match(RegularExpressionsUtils.EMAIL_ADDRESS_REGEXP, email);
	}
	
	def "Email invalido: sin punto"() {
		when:
			def email = "emaildeprueba@gmailcom"
		then:
			!RegularExpressionsUtils.match(RegularExpressionsUtils.EMAIL_ADDRESS_REGEXP, email);
	}
	
	def "Email invalido: sin arroba"() {
		when:
			def email = "emaildepruebagmail.com"
		then:
			!RegularExpressionsUtils.match(RegularExpressionsUtils.EMAIL_ADDRESS_REGEXP, email);
	}
	
	/* PASSWORD REGEXP */
	def "Password valida: mayuscula seguida de dos numeros"() {
		when:
			def password = "passwordT35st"
		then:
			RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password valida: mayuscula entre medio de dos numeros"() {
		when:
			def password = "passwordt3S5t"
		then:
			RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password valida: numeros seguidos de una mayuscula"() {
		when:
			def password = "passwordt35Tt"
		then:
			RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password valida: mayuscula y numeros separados"() {
		when:
			def password = "pAssw0rdte5t"
		then:
			RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password invalida: sin mayusculas"() {
		when:
			def password = "passw0rdte5t"
		then:
			!RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password invalida: mas de una mayuscula"() {
		when:
			def password = "passw0rDTe5t"
		then:
			!RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password invalida: un solo numero"() {
		when:
			def password = "passw0rDTest"
		then:
			!RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
	
	def "Password invalida: mas de dos numeros"() {
		when:
			def password = "p4ssw0rDTe5t"
		then:
			!RegularExpressionsUtils.match(RegularExpressionsUtils.PASSWORD_REGEXP, password);
	}
}