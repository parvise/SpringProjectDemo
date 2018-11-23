package com.demo.flashSale.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.flashSale.entity.PurchaseDateTimeEntity;

@Repository
public interface PurchasedDateTimeDao extends CrudRepository<PurchaseDateTimeEntity, String> {

}
