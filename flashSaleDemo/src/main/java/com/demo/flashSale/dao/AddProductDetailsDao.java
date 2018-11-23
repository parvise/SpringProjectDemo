package com.demo.flashSale.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.flashSale.entity.ProductDetailsEntity;

@Repository
public interface AddProductDetailsDao extends CrudRepository<ProductDetailsEntity, Integer> {

}
