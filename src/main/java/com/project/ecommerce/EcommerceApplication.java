package com.project.ecommerce;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.ecommerce.entities.Booking;
import com.project.ecommerce.entities.BookingConfig;
import com.project.ecommerce.entities.ERole;
import com.project.ecommerce.entities.Role;
import com.project.ecommerce.repositories.BookingConfigRepository;
import com.project.ecommerce.repositories.RoleRepository;
import com.project.ecommerce.services.EmailSenderService;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BookingConfigRepository bookingConfigRepository;

	@Autowired
    private EmailSenderService emailSenderService;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();
			adminRole.setId(101);
			adminRole.setName(ERole.ROLE_ADMIN);

			Role userRole = new Role();
			userRole.setId(102);
			userRole.setName(ERole.ROLE_USER);

			List<Role> roles = List.of(adminRole, userRole);

			List<Role> savedRoles = roleRepository.saveAll(roles);

			savedRoles.forEach(System.out::println);

			// Create booking config instance

			BookingConfig checkConfig = bookingConfigRepository.findById("SOKE").orElse(null);

			if(checkConfig == null) {
				BookingConfig config = new BookingConfig();
				config.setId("SOKE");
				config.setMaxPlaceTakeCare(10);
				config.setCurrentTakeCareBooking(0);
				config.setMaxPlaceExamination(10);
				config.setCurrentExaminationBooking(0);

				config.setPriceSlotSizeS(5.0);
				config.setPriceSlotSizeM(7.0);
				config.setPriceSlotSizeL(10.0);

				config.setFood1("Meats");
				config.setFood1Image("food1.jpg");
				config.setFood1Price(30.0);

				config.setFood2("Vegetable");
				config.setFood2Image("food2.jpg");
				config.setFood2Price(15.0);

				config.setFood3("Seed");
				config.setFood3Image("food3.jpg");
				config.setFood3Price(5.0);

				config.setService1("Grooming");
				config.setService1Price(5.0);
				config.setService2("Matxa");
				config.setService2Price(10.0);
				bookingConfigRepository.save(config);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
