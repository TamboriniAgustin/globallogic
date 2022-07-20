package global.logic.bci.test.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Phone {
	private long number;
	private int cityCode;
	private String countryCode;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cityCode;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + (int) (number ^ (number >>> 32));
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
		Phone other = (Phone) obj;
		if (cityCode != other.cityCode) {
			return false;
		}
		if (countryCode == null) {
			if (other.countryCode != null) {
				return false;
			}
		} else if (!countryCode.equals(other.countryCode)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		return true;
	}
}