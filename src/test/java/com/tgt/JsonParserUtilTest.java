package com.tgt;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.api.ProductApiPojo;
import com.tgt.response.ProductPricing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JsonParserUtilTest {
//Output JSOn Example: {\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}
    private String productPricingTestJSONString = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

   /* @Test
    public void testToJsonObject() {

        User user = new User();
        user.setBio("bio mate");
        user.setCountry("uk");
        user.setEmailAddress("jonney@hello.com");
        user.setFirstName("jono");
        user.setPassword("passwordfdsadsa");
        user.setUsername("crazy8");
        JSONObject jsonUser =  JsonPojo.toJsonObject(user);
        assertNotNull(jsonUser);
        assertNotNull(jsonUser.keys());
        System.out.println(jsonUser);

    }*/

    @Test
    public void testToObject(){
    	ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    	try {
			ProductPricing productPricingTestJSON = mapper.readValue(productPricingTestJSONString, ProductPricing.class);
	        assertNotNull(productPricingTestJSON);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	
    }

	
