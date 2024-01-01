package com.project.ecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.ecommerce.repositories.RoleRepository;

@SpringBootApplication
public class EcommerceApplication {
	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	try {
	// 		Role adminRole = new Role();
	// 		adminRole.setRoleId(AppConstants.ADMIN_ID);
	// 		adminRole.setRoleName("ADMIN");

	// 		Role userRole = new Role();
	// 		userRole.setRoleId(AppConstants.USER_ID);
	// 		userRole.setRoleName("USER");

	// 		List<Role> roles = List.of(adminRole, userRole);

	// 		List<Role> savedRoles = roleRepo.saveAll(roles);

	// 		savedRoles.forEach(System.out::println);

	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
}
