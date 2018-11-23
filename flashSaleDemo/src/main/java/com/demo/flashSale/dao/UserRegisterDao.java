package com.demo.flashSale.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.flashSale.entity.RegisterEntity;

@Repository
public interface UserRegisterDao extends CrudRepository<RegisterEntity, String> {

	// 3). Example for Spring Data by Query Annoatations
	@Query(value = "SELECT p FROM RegisterEntity p WHERE p.mailId=?1 AND p.regId=?2", nativeQuery = false)
	RegisterEntity findUserRegistration(String mailId, String regId);
}
