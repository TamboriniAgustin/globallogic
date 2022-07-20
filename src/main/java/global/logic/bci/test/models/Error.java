package global.logic.bci.test.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Error {
	private Timestamp timestamp;
	private int codigo;
	private String detail;
	
	public Error(int codigo, String detail) {
		this.codigo = codigo;
		this.detail = detail;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		Error other = (Error) obj;
		if (codigo != other.codigo) {
			return false;
		}
		if (detail == null) {
			if (other.detail != null) {
				return false;
			}
		} else if (!detail.equals(other.detail)) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}
}