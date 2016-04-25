package com.tgt.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tgt.mongodb.Product;

/**
 * @author BonyThomas:
 * @Description: A Mongo DB repository class 
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    public List<Product> findByProductId(String productId);
    public List<Product> findByCurrentPrice(double currentPrice);
    public List<Product> findByCurrencyCode(String currencyCode);

}