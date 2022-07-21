package global.logic.bci.test.utils.encoding;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class Base64Encoder implements PasswordEncoder {
	@Override
	public String encodePassword(String password) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(password.getBytes());
	}
}