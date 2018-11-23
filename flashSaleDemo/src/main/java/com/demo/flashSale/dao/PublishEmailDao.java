package com.demo.flashSale.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.flashSale.entity.EmailIdEntity;

public interface PublishEmailDao extends CrudRepository<EmailIdEntity, String> {

	// 1). Example for Spring Data By Method Names
	EmailIdEntity findByEmailAddress(String mailId);

	//2). Example for Spring Data By Named Queries withh named Params
	EmailIdEntity getByEmailAddress(@org.springframework.data.repository.query.Param("emailAddress") String mailId);

	// 3). Example for Spring Data by Query Annoatations
	@Query(value = "SELECT p FROM EmailIdEntity p WHERE p.emailAddress=?1", nativeQuery = false)
	EmailIdEntity findEmailAddress(String mailId);

}
