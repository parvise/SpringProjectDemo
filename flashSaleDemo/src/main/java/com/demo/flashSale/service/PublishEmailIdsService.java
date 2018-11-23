package com.demo.flashSale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.flashSale.dao.PublishEmailDao;
import com.demo.flashSale.entity.EmailIdEntity;

@Service
public class PublishEmailIdsService {

	@Autowired
	private PublishEmailDao dao;

	public void saveData(EmailIdEntity entity) {
		dao.save(entity);
	}

	public EmailIdEntity findEmailIdDetails(String mailId) {
		return dao.findByEmailAddress(mailId);
	}

	public EmailIdEntity getByEmailAddress(String mailId) {
		return dao.getByEmailAddress(mailId);
	}

	public EmailIdEntity findEmailAddress(String mailId) {
		return dao.findEmailAddress(mailId);
	}

	public void updateEmailIdDetails(EmailIdEntity check2) {
		Optional<EmailIdEntity> update = dao.findById(check2.getEmailId());

		if (update.isPresent()) {
			EmailIdEntity test = update.get();
			if (test.getEmailId().equals(check2.getEmailId())) {
				dao.save(check2);
				System.out.println("Updated Success");
			}
		}
	}

	public Iterable<EmailIdEntity> listAllEmailIds() {
		return dao.findAll();
	}

	public EmailIdEntity findById(String mailId) {
		Optional<EmailIdEntity> optional = dao.findById(mailId);
		return optional.get();
	}
}
