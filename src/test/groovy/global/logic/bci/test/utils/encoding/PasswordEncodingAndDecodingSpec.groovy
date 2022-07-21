package global.logic.bci.test.utils.encoding;

import spock.lang.Specification

class PasswordEncodingAndDecodingSpec extends Specification {
	private static final String PASSWORD_TEST = "p4sswordDePrueba";
	
	/* BASE64 */
	def "Encripcion BASE64"() {
		given:
			def encoder = new Base64Encoder()
			def decoder = new Base64Decoder()
		when:
			def encodedPassword = encoder.encodePassword(PASSWORD_TEST)
			def decodedPassword = decoder.decodePassword(encodedPassword)
		then:
			PASSWORD_TEST.equals(decodedPassword)
	}
}