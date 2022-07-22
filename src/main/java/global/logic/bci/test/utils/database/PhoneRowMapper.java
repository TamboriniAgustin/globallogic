package global.logic.bci.test.utils.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import global.logic.bci.test.models.Phone;

public class PhoneRowMapper implements RowMapper<Phone> {
	@Override
	public Phone mapRow(ResultSet rs, int rowNum) throws SQLException {
		Phone phone = new Phone();
		
		phone.setNumber(rs.getLong("phone_number"));
		phone.setCityCode(rs.getInt("phone_city_code"));
		phone.setCountryCode(rs.getString("phone_country_code"));
		
		return phone;
	}
}