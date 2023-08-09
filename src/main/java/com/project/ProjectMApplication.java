package com.project;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.project.dao.TestDAO;

@SpringBootApplication
public class ProjectMApplication {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TestDAO testDAO;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectMApplication.class, args);
	}
	
	@Bean
	CommandLineRunner getCommandLineRunner() {
		return(args)->{
			System.out.println("-".repeat(80));
			@SuppressWarnings("deprecation")
			String today = jdbcTemplate.queryForObject("select current_timestamp from dual",null, String.class);
			System.out.println("jdbcTemplate을 이용한 마리아 DB 시간 : " + today);
			// 위에 3번을 처리하고 마이바티스 연동 테스트
			System.out.println("Mybatis를 이용한 마리아 DB 시간 : " + testDAO.selectToday());
			System.out.println("-".repeat(80));
		};
	}

}
