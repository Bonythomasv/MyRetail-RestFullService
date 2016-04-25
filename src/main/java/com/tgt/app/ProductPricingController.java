package com.tgt.app;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgt.app.ProductService;
import com.tgt.mongodb.Product;
import com.tgt.response.CurrentPrice;
import com.tgt.response.ProductPricing;

/**
 * @author BonyThomas:
 * @Description: This controller provides the REST methods 
 */

@RestController
public class ProductPricingController  {
	
	// Spring DI inject the service 
    @Autowired
    private ProductService productService;

    /*
     * @Description: HTTP GET method for querying the Product Information
     * @param: UserId from URL
     * @return: ProductPricing JSON 
     * Sample Response JSON is : {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
     */
	@RequestMapping(value= "/products/{id}",method = RequestMethod.GET)
    public ProductPricing productPricing(@PathVariable(value="id") String Id) throws Exception {
	//System.out.println("ID is "+Id);
		ProductPricing productPricing =  new ProductPricing();
		String regex = "[0-9]+"; 
		if(Id==null || !Id.matches(regex))
		{
			productPricing.setErrorMsg("Product Id in URL arguments should be a number");
		}
		else		
		{
			 productPricing= productService.searchProductPrice(Id.toString());
			if (productPricing==null || productPricing.getCurrentPrice() == null)
			{		
				productPricing.setErrorMsg("No Products Found with the Id "+Id);
			}
		}
	
    	return productPricing;
    }
	
	
	  /*
     * @Description: //inserting product Price to Mongo DB: Pay Load JSON data will be inserted
     * @param: ProductPricing JSON
     * @return: success/failure Message 
     */
	@RequestMapping(value= "/products/{id}",method = RequestMethod.PUT)
	public String productPricing(@PathVariable(value="id") String Id, @RequestBody Map<String, Object> insertProductPricing ) throws Exception {
		System.out.println("ID is "+Id);
		String responseMsg ="";
		String regex = "[0-9]+"; 
		System.out.println("Id.matches(regex) is "+Id.matches(regex));
		if(Id==null || !Id.matches(regex) || insertProductPricing == null)
		{
			responseMsg= "In correct Data!!! Please vallidate Id or RequestBody";
		}
		else
		{
		if( productService.insertNewProduct(insertProductPricing) == true)
			responseMsg = "Product Data Inserted Successfully"; 
		   else 
			   responseMsg = "Product Data Insert Failed"; 
	    }
		return responseMsg;
	}
	
	
	

   /*
   * @Description: To handle improper URL arguments. This helps to avoid Querying the entire data in Mongo DB
   * @return: Error Message 
   */
	@RequestMapping(value= "/products",method = RequestMethod.GET)
    public ProductPricing productPricingNoArgs() throws Exception {
		ProductPricing productPricing = new ProductPricing();
		productPricing.setErrorMsg("Product Id is missing in Rest Service URL arguments ");
		return productPricing;
    	
    }
	
	  
    
}
