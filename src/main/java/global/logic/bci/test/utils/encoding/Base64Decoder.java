package global.logic.bci.test.utils.encoding;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.stereotype.Component;

@Component
public class Base64Decoder implements PasswordDecoder {
	@Override
	public String decodePassword(String encodedPassword) {
		Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(encodedPassword.getBytes()));
	}
}