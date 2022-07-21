package global.logic.bci.test.utils;

import java.util.regex.Pattern;

public class RegularExpressionsUtils {
	public static final Pattern EMAIL_ADDRESS_REGEXP = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern PASSWORD_REGEXP = Pattern.compile("^([a-z]*)([0-9]{1}[a-z]*[A-Z]{1}[a-z]*[0-9]{1}|[0-9]{1}[a-z]*[0-9]{1}[a-z]*[A-Z]{1}|[A-Z]{1}[a-z]*[0-9]{1}[a-z]*[0-9]{1})([a-z]*)$");
	
	/*
	 	Chequea si un valor coincide con una determinada regular expression
	 	Params: @pattern (regular expression) - @value (valor a comparar)
	*/
	public static boolean match(Pattern pattern, String value) {
	    return pattern.matcher(value).find();
	}
}