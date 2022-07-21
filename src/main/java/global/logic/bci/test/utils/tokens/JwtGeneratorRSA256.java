package global.logic.bci.test.utils.tokens;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtGeneratorRSA256 implements JwtGenerator {
	private static final String METHOD = "RSA";
	private static final int KEY_SIZE = 2048;
	
	@Override
	public String generateJwtToken(Map<String, String> claims) throws NoSuchAlgorithmException {
		/* Generamos el keypair correspondiente (para manejo de claves publicas y privadas) */
		KeyPair keyPair = getKeyPair(METHOD, KEY_SIZE);
		
		/* Generamos el builder del token y lo completo */
		Builder tokenBuilder = JWT.create();
		
		//Tomamos los claims específicos del usuario/elemento para el que se quiera el Token
		claims.entrySet().forEach(action -> { 
			tokenBuilder.withClaim(action.getKey(), action.getValue());
		});
		//Seteamos el momento en que se está generando la key
		tokenBuilder.withIssuedAt(Date.from(Instant.now()));
			
		return tokenBuilder.sign(Algorithm.RSA256(((RSAPublicKey) keyPair.getPublic()), ((RSAPrivateKey) keyPair.getPrivate())));
	}
}