package global.logic.bci.test.models.response;

import java.util.List;

import global.logic.bci.test.models.Error;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestResponse {
	private List<Error> errors;
	
	public void addError(Error error) {
		this.errors.add(error);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RequestResponse other = (RequestResponse) obj;
		if (errors == null) {
			if (other.errors != null) {
				return false;
			}
		} else if (!errors.equals(other.errors)) {
			return false;
		}
		return true;
	}
}