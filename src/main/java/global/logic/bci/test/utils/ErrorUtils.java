package global.logic.bci.test.utils;

import global.logic.bci.test.models.Error;
import global.logic.bci.test.models.ErrorCodes;

public class ErrorUtils {
	public static Error generateError(ErrorCodes error) {
		return new Error(error.getCode(), error.getReason());
	}
}