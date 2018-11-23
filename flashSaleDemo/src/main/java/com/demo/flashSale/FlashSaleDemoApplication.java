package com.demo.flashSale;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.flashSale.entity.EmailIdEntity;
import com.demo.flashSale.service.PublishEmailIdsService;

@SpringBootApplication
public class FlashSaleDemoApplication implements CommandLineRunner {

	@Autowired
	private PublishEmailIdsService service;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(FlashSaleDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome");
		System.out.println(dataSource);

	/*	// createNewEMailId();
		EmailIdEntity check = findEmailIdDetails("parvise.md20@gmail.com");
		System.out.println("**********8" + check);

		EmailIdEntity check1 = getByEmailAddress("bhardwajsushant4@gmail.com");
		System.out.println("**********8" + check1);

		EmailIdEntity check2 = findEmailAddress("sheiksalma34@gmail.com");
		System.out.println("**********8" + check2);

		//check2.setEmailAddress("sheiksalma34@gmail.comm");

		//updateEmailIdDetails(check2);

		Iterable<EmailIdEntity> list = listAllEmailIds();
		for (EmailIdEntity emailIdEntity : list) {
			System.out.println(emailIdEntity + "******");
		}
*/
	}

	private Iterable<EmailIdEntity> listAllEmailIds() {
		return service.listAllEmailIds();
	}

	private void updateEmailIdDetails(EmailIdEntity check2) {
		service.updateEmailIdDetails(check2);
	}

	private EmailIdEntity findEmailAddress(String mailId) {
		return service.findEmailAddress(mailId);
	}

	private EmailIdEntity findEmailIdDetails(String mailId) {
		return service.findEmailIdDetails(mailId);
	}

	private EmailIdEntity getByEmailAddress(String mailId) {
		return service.getByEmailAddress(mailId);
	}

	private void createNewEMailId() {
		EmailIdEntity entity = new EmailIdEntity();
		entity.setEmailId(UUID.randomUUID().toString());
		entity.setEmailAddress("sheiksalma34@gmail.com");
		entity.setMobileNumber("9676858357");
		service.saveData(entity);
	}
}
