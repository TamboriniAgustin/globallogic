package global.logic.bci.test.utils.tokens;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@FunctionalInterface
public interface JwtGenerator {
	public String generateJwtToken(Map<String, String> claims) throws NoSuchAlgorithmException;
	
	default KeyPair getKeyPair(String method, int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(method);
		keyPairGenerator.initialize(keySize);
		return keyPairGenerator.generateKeyPair();
	}
}