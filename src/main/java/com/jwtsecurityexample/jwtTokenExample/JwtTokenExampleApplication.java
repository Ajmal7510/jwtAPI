package com.jwtsecurityexample.jwtTokenExample;

import com.jwtsecurityexample.jwtTokenExample.Models.ApplicationUser;
import com.jwtsecurityexample.jwtTokenExample.Models.Role;
import com.jwtsecurityexample.jwtTokenExample.Repository.RoleRepository;
import com.jwtsecurityexample.jwtTokenExample.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JwtTokenExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenExampleApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigures() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000");
			}

		};
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole=roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles=new HashSet<>();
			roles.add(adminRole);
			ApplicationUser admin=new ApplicationUser(1,"admin",bCryptPasswordEncoder.encode("password"),roles);

			userRepository.save(admin);

		};

	}

}
