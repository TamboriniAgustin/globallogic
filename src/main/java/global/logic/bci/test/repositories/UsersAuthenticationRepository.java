package global.logic.bci.test.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import global.logic.bci.test.models.NewUser;
import global.logic.bci.test.models.Phone;

@Repository
public class UsersAuthenticationRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_USER = "INSERT INTO `users`(`user_id`, `user_name`, `user_email`, `user_password`, "
			+ "`user_created`, `user_last_login`, `user_token`, `user_is_active`) VALUES(?,?,?,?,?,?,?,?)";
	private static final String INSERT_USER_PHONE = "INSERT INTO `phones`(`phone_number`, `phone_city_code`, `phone_country_code`, "
			+ "`user_id`) VALUES(?,?,?,?)";
	private static final String DELETE_USER = "DELETE FROM `users` WHERE `user_id` = ?";
	
	/*Inserts*/
	public void insertUser(NewUser userInformation) {
		jdbcTemplate.update(INSERT_USER, userInformation.getId(), userInformation.getName(), userInformation.getEmail(), 
			userInformation.getPassword(), userInformation.getCreated(), userInformation.getLastLogin(), userInformation.getToken(), 
			userInformation.isActive());
	}
	
	public void insertPhone(String userId, Phone phone) {
		jdbcTemplate.update(INSERT_USER_PHONE, phone.getNumber(), phone.getCityCode(), phone.getCountryCode(), userId);
	}
	
	/*Selects*/
	
	/*Updates*/
	
	/*Deletes*/
	public void deleteUser(String userId) {
		jdbcTemplate.update(DELETE_USER, userId);
	}
}