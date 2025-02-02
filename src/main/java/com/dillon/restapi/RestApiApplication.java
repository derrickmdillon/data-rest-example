package com.dillon.restapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dillon.restapi.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RestApiApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE users IF EXISTS");

		jdbcTemplate.execute("DROP TABLE test IF EXISTS");
		jdbcTemplate.execute("DROP TABLE test_run IF EXISTS");
		jdbcTemplate.execute("DROP TABLE test_results IF EXISTS");

		jdbcTemplate.execute("CREATE TABLE users(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		jdbcTemplate.execute("CREATE TABLE test(test_id SERIAL, test_name VARCHAR(255), bug VARCHAR(255))");
		jdbcTemplate.execute("""
		CREATE TABLE test_run(
			test_run_id SERIAL, 
			test_id INT, 
			plant VARCHAR(255), 
			area VARCHAR(255),
			foreign key (test_id) references test(test_id)
			)""");
		jdbcTemplate.execute("""
		CREATE TABLE test_results(
			test_result_id SERIAL, 
			test_run_id INT, 
			name VARCHAR(255), 
			value VARCHAR(255),
			foreign key (test_run_id) references test_run(test_run_id)
		)""");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> users = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" ")).collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		users.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO users(first_name, last_name) VALUES (?,?)", users);

		log.info("Querying for customer records where name = 'Josh':");
		jdbcTemplate.query("SELECT id, first_name, last_name FROM users WHERE first_name = ?", new Object[] { "Josh" },
				(rs, rowNum) -> new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
				.forEach(user -> log.info(user.toString()));
	}

}
