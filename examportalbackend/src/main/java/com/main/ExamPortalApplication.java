package com.main;

import com.main.helper.UserFoundException;
import com.main.model.Role;
import com.main.model.User;
import com.main.model.UserRole;
import com.main.model.exam.Quiz;
import com.main.Repository.QuizRepository;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public QuizRepository quizRepository;

	public static void main(String[] args) {

		SpringApplication.run(ExamPortalApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		try {

			System.out.println("starting code");
//

			User user = new User();

			user.setFirstName("sri");
			user.setLastName("latha");
			user.setUsername("SriLatha");
			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
			user.setEmail("abc@gmail.com");
			user.setProfile("default.png");

			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();

			userRole.setRole(role1);

			userRole.setUser(user);

			userRoleSet.add(userRole);

			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());

		} catch (UserFoundException e) {
			e.printStackTrace();
		}
	}
}
