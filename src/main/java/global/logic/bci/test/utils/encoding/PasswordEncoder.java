package global.logic.bci.test.utils.encoding;

@FunctionalInterface
public interface PasswordEncoder {
	public String encodePassword(String password);
}