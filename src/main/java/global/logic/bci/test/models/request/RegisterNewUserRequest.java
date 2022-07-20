package global.logic.bci.test.models.request;

import java.util.List;

import global.logic.bci.test.models.Phone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterNewUserRequest {
	private String name;
	private String email;
	private String password;
	private List<Phone> phones;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
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
		RegisterNewUserRequest other = (RegisterNewUserRequest) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (phones == null) {
			if (other.phones != null) {
				return false;
			}
		} else if (!phones.equals(other.phones)) {
			return false;
		}
		return true;
	}
}