package com.tgt.app.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tgt.app.db.Product;

/**
 * @author BonyThomas:
 * @Description: A Mongo DB repository class 
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    public List<Product> findByProductId(String productId);
    public List<Product> findByCurrentPrice(double currentPrice);
    public List<Product> findByCurrencyCode(String currencyCode);

}