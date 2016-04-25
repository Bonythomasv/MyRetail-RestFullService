package com.tgt.app;

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
import com.tgt.mongodb.Product;
import com.tgt.response.CurrentPrice;
import com.tgt.response.ProductPricing;

/**
 * @author BonyThomas:
 * @Description: This is a  service class for API call and Insert New data using HTTP PUT
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
     * @param: String Product Id from URL
     * @return: ProductPricing 
     */
	public ProductPricing searchProductPrice(String productId) {

		//call the Target API to get Product Name
				String productName = targetApiCall(productId);
				log.info("productName from API call is "+productName);
				
		System.out.println("productId in SearchProductPrice "+productId);
		CurrentPrice currentPrice = new CurrentPrice();
		ProductPricing 	productPricing = new ProductPricing();
		//insertDataMongoDB();
		System.out.println("Customer found with findByProductId(productId):");
		System.out.println("--------------------------------");
		for (Product product : repository.findByProductId(productId))
		{
			System.out.println("ProductPrice With productId is"+product.getCurrentPrice());
			System.out.println("ProductCurrency With productId is"+product.getCurrencyCode());

			//form the response JSON
	    	 currentPrice.setCurrencyCode(product.getCurrencyCode()); 
	    	 currentPrice.setValue(product.getCurrentPrice());
	    	 productPricing.setId(Integer.parseInt(productId));
	    	 productPricing.setName(productName);
	    	 productPricing.setCurrentPrice(currentPrice);
	    	 System.out.println("productPricing is "+productPricing);
		}
		
		
		return productPricing ;
       
	}
	
	/*
     * @Description: API call to Fetch the Product Name
     * @param: String ProductId from URL
     * @return: String Product Name
     */
	public String targetApiCall(String productId)
	{
		
		 RestTemplate restTemplate = new RestTemplate();
		 ProductApiPojo productApiPojo = restTemplate.getForObject("https://api.target.com/products/v3/"+productId+"?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz", ProductApiPojo.class);
	     String productName = "" ;
	        System.out.println("productApiPojo is "+productApiPojo.getProductCompositeResponse().getItems().get(0).getGeneralDescription());
	        productName = productApiPojo.getProductCompositeResponse().getItems().get(0).getGeneralDescription();
		return productName;
		
	}
	
	/*
     * @Description: Function for inserting new records into Mongo DB
     * @param: map Map<String, String> 
     * @return: boolean
     */
	public boolean insertNewProduct(Map<String, Object> insertProductPricingMap)
	{
		try{
			System.out.println("Product Pricing Map to be inserted  In Service is"+insertProductPricingMap);
			
			
			Map<String, String> insertProductPricingStringMap = (Map) insertProductPricingMap;
			System.out.println("insertProductPricingStringMap is "+insertProductPricingStringMap.toString());
			Gson gson = new Gson();
	        String jsonString = gson.toJson(insertProductPricingStringMap);
	        System.out.println("Converted GSON JSON is "+jsonString);
	        
			ObjectMapper mapper = new ObjectMapper();
			ProductPricing insertProductPricing =  mapper.readValue(jsonString, ProductPricing.class);
			System.out.println("ProductPricing Id is "+insertProductPricing.getId());
			//insert the product price to DB
			repository.save(new Product(insertProductPricing.getId().toString(), insertProductPricing.getCurrentPrice().getValue(), insertProductPricing.getCurrentPrice().getCurrencyCode()));
			return true;

		   }
      catch(Exception error)
		{
	      System.out.println("Exception"+error);  
    	  return false;
		}
		
	}
	
	/*
     * @Description: Function for Cleaning the Local Mongo DB database and insert new Fresh data
     * @param: noe
     * @return: none
     */
	public void insertDataMongoDB()
	{
		//clean the mongo repository
				repository.deleteAll();
				// Code for Saving New data to the repository
				repository.save(new Product("15117729", 12.67, "USD"));
				repository.save(new Product("13860428", 13.49, "USD"));
				repository.save(new Product("16483589", 45.99, "USD"));
				repository.save(new Product("16696652", 24.56, "USD"));
				repository.save(new Product("16752456", 67.45, "USD"));
				repository.save(new Product("15643793", 10.99, "USD"));
		        
				//fetch all Products
				System.out.println("Customers found with findAll():");
				System.out.println("-------------------------------");
				for (Product product : repository.findAll()) {
					System.out.println(product);
				}
				System.out.println(); 
	}}
