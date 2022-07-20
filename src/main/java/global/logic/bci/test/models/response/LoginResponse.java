package global.logic.bci.test.models.response;

import java.util.List;

import global.logic.bci.test.models.Phone;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse extends RequestResponse {
	private String id;
	private String created;
	private String lastLogin;
	private String token;
	private String name;
	private String email;
	private String password;
	private List<Phone> phones;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LoginResponse other = (LoginResponse) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastLogin == null) {
			if (other.lastLogin != null) {
				return false;
			}
		} else if (!lastLogin.equals(other.lastLogin)) {
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
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		return true;
	}
}