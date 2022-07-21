package global.logic.bci.test.utils.encoding;

@FunctionalInterface
public interface PasswordDecoder {
	public String decodePassword(String encodedPassword);
}
