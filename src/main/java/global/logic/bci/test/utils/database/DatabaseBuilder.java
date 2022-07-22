package global.logic.bci.test.utils.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseBuilder {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseBuilder.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* Drops */
	private static final String DROP_EXISTING_TABLE_USERS = "DROP TABLE `users` IF EXISTS";
	private static final String DROP_EXISTING_TABLE_PHONES = "DROP TABLE `phones` IF EXISTS";
	/* Creates */
	private static final String CREATE_TABLE_USERS = "CREATE TABLE `users`(`user_id` varchar(36) NOT NULL, "
			+ "`user_name` varchar(60) NOT NULL, `user_email` varchar(60) NOT NULL, `user_password` varchar(60) NOT NULL, "
			+ "`user_created` varchar(30) NOT NULL, `user_last_login` varchar(30) NOT NULL, `user_token` text NOT NULL, "
			+ "`user_is_active` tinyint NOT NULL)";
	private static final String CREATE_TABLE_PHONES = "CREATE TABLE `phones`(`phone_number` bigint NOT NULL, "
			+ "`phone_city_code` int NOT NULL, `phone_country_code` varchar(5) NOT NULL, "
			+ "`user_id` varchar(36) NOT NULL)";
	/* Primary Keys */
	private static final String ADD_PRIMARY_KEYS_TABLE_USERS = "ALTER TABLE `users` "
			+ "ADD PRIMARY KEY(`user_id`)";
	private static final String ADD_PRIMARY_KEYS_TABLE_PHONE = "ALTER TABLE `phones` "
			+ "ADD PRIMARY KEY(`phone_number`, `phone_city_code`, `phone_country_code`)";
	/* Foreign Keys */
	private static final String ADD_FOREIGN_KEYS_TABLA_PHONE = "ALTER TABLE `phones` ADD FOREIGN KEY(`user_id`) "
			+ "REFERENCES `users`(`user_id`)";
	/* Unique Keys */
	private static final String ADD_UNIQUE_KEYS_USERS = "ALTER TABLE `users` ADD UNIQUE(`user_email`)";
	
	
	
	public void generateDatabase() {
		dropTables();
		createTables();
		addKeys();
		logger.debug("Base de datos generada con exito");
	}
	
	
	
	
	private void dropTables() {
		jdbcTemplate.execute(DROP_EXISTING_TABLE_PHONES);
		jdbcTemplate.execute(DROP_EXISTING_TABLE_USERS);
	}
	
	private void createTables() {
		jdbcTemplate.execute(CREATE_TABLE_USERS);
		jdbcTemplate.execute(CREATE_TABLE_PHONES);
	}
	
	private void addKeys() {
		addPrimaryKeys();
		addForeignKeys();
		addUniqueKeys();
	}
	
	private void addPrimaryKeys() {
		jdbcTemplate.execute(ADD_PRIMARY_KEYS_TABLE_USERS);
		jdbcTemplate.execute(ADD_PRIMARY_KEYS_TABLE_PHONE);
	}
	
	private void addForeignKeys() {
		jdbcTemplate.execute(ADD_FOREIGN_KEYS_TABLA_PHONE);
	}
	
	private void addUniqueKeys() {
		jdbcTemplate.execute(ADD_UNIQUE_KEYS_USERS);
	}
}