package com.tgt.app.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tgt.api.ProductApiPojo;
import com.tgt.app.db.Product;
import com.tgt.app.db.ProductRepository;
import com.tgt.response.CurrentPrice;
import com.tgt.response.ProductPricing;

/**
 * @author BonyThomas:
 * @Description: This is a service class for API call and Insert New data using
 *               HTTP PUT
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	public List getProducts() {
		return repository.findAll();
	}

	/*
	 * @Description: Search the product Price in Mongo DB data Base
	 * 
	 * @param: String Product Id from URL
	 * 
	 * @return: ProductPricing
	 */
	public ProductPricing searchProductPrice(String productId) {

		// call the Target API to get Product Name
		String productName = targetApiCall(productId);
		log.info("productName from API call is " + productName);

		log.info("productId in SearchProductPrice " + productId);

		CurrentPrice currentPrice = new CurrentPrice();
		ProductPricing productPricing = new ProductPricing();
		// insertDataMongoDB();
		log.info("Customer found with findByProductId(productId): " + productId);
		System.out.println("--------------------------------");
		log.info("--------------------------------");
		for (Product product : repository.findByProductId(productId)) {
			log.info("ProductPrice With productId is" + product.getCurrentPrice());
			log.info("ProductCurrency With productId is" + product.getCurrencyCode());

			// form the response JSON
			currentPrice.setCurrencyCode(product.getCurrencyCode());
			currentPrice.setValue(product.getCurrentPrice());
			productPricing.setId(Integer.parseInt(productId));
			productPricing.setName(productName);
			productPricing.setCurrentPrice(currentPrice);
			log.info("productPricing is " + productPricing);
		}

		return productPricing;

	}

	/*
	 * @Description: API call to Fetch the Product Name
	 * 
	 * @param: String ProductId from URL
	 * 
	 * @return: String Product Name
	 */
	public String targetApiCall(String productId) {

		RestTemplate restTemplate = new RestTemplate();
		ProductApiPojo productApiPojo = restTemplate.getForObject(
				"https://api.target.com/products/v3/" + productId
						+ "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz",
				ProductApiPojo.class);
		String productName = "";
		// log.info("productPricing is "+"productApiPojo is
		// "+productApiPojo.getProductCompositeResponse().getItems().get(0).getGeneralDescription());
		// Product Name is not available in API JSON response , Hence the
		// closest value of General Description is choosen as Product Name
		// Add Null pointer Check
		productName = productApiPojo.getProductCompositeResponse().getItems().get(0).getGeneralDescription();
		return productName;

	}

	/*
	 * @Description: Function for inserting new records into Mongo DB
	 * 
	 * @param: map Map<String, String>
	 * 
	 * @return: boolean
	 */
	public boolean insertNewProduct(Map<String, Object> insertProductPricingMap) {
		try {
			log.info("Product Pricing Map to be inserted  In Service is" + insertProductPricingMap);

			Map<String, String> insertProductPricingStringMap = (Map) insertProductPricingMap;
			log.info("insertProductPricingStringMap is " + insertProductPricingStringMap.toString());
			Gson gson = new Gson();
			String jsonString = gson.toJson(insertProductPricingStringMap);
			log.info("Converted GSON JSON is " + jsonString);

			ObjectMapper mapper = new ObjectMapper();
			ProductPricing insertProductPricing = mapper.readValue(jsonString, ProductPricing.class);
			log.info("ProductPricing Id is " + insertProductPricing.getId());
			// insert the product price to DB
			repository.save(new Product(insertProductPricing.getId().toString(),
					insertProductPricing.getCurrentPrice().getValue(),
					insertProductPricing.getCurrentPrice().getCurrencyCode()));
			return true;

		} catch (Exception error) {
			log.info("Exception" + error);
			return false;
		}

	}

	/*
	 * @Description: Function for Cleaning the Local Mongo DB database and
	 * insert new Fresh data
	 * 
	 * @param: noe
	 * 
	 * @return: none
	 */
	public void insertDataMongoDB() {
		// clean the mongo repository
		repository.deleteAll();
		// Code for Saving New data to the repository
		repository.save(new Product("15117729", 12.67, "USD"));
		repository.save(new Product("13860428", 13.49, "USD"));
		repository.save(new Product("16483589", 45.99, "USD"));
		repository.save(new Product("16696652", 24.56, "USD"));
		repository.save(new Product("16752456", 67.45, "USD"));
		repository.save(new Product("15643793", 10.99, "USD"));

		// fetch all Products
		log.info("-------------------------------");
		log.info("Customers found with findAll():");
		for (Product product : repository.findAll()) {
			System.out.println(product);
			log.info("product in Loop " + product);
		}

	}
}
