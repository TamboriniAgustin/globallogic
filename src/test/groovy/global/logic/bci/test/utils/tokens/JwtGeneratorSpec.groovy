package global.logic.bci.test.utils.tokens;

import java.security.NoSuchAlgorithmException

import spock.lang.Specification

class JwtGeneratorSpec extends Specification {
	def "Solicitud invalida: no se enviaron claims"() {
		given:
			def generator = new JwtGeneratorRSA256()
		when:
			generator.generateJwtToken(null)
		then:
			thrown(NullPointerException)
	}
	
	/* RSA256 */
	def "Solicitud valida: RSA256"() {
		given:
			def generator = new JwtGeneratorRSA256()
			def claims = new HashMap<>()
			claims.put("test", "test")
		when:
			def result = generator.generateJwtToken(claims)
		then:
			result.length() > 0
	}
}