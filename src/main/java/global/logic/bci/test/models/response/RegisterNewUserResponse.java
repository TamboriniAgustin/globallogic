package global.logic.bci.test.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterNewUserResponse extends RequestResponse {
	private String id;
	private String created;
	private String lastLogin;
	private String token;
	private boolean isActive;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
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
		RegisterNewUserResponse other = (RegisterNewUserResponse) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (isActive != other.isActive) {
			return false;
		}
		if (lastLogin == null) {
			if (other.lastLogin != null) {
				return false;
			}
		} else if (!lastLogin.equals(other.lastLogin)) {
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