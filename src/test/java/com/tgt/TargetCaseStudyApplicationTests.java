package com.tgt;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.api.ProductApiPojo;
import com.tgt.app.TargetCaseStudyApplication;
import com.tgt.response.ProductPricing;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TargetCaseStudyApplication.class)
@WebAppConfiguration
public class TargetCaseStudyApplicationTests {
	RestTemplate template = new TestRestTemplate();
	
	/*@Autowired
	private MockMvc mvc;

	@Autowired
    private TestRestTemplate restTemplate;*/

    private String productPricingTestJSONString = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

    private String productApiPojoTestString = "{\"product_composite_response\":{\"request_attributes\":[{\"name\":\"product_id\",\"value\":\"13860418\"},{\"name\":\"key\",\"value\":\"43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz\"},{\"name\":\"id_type\",\"value\":\"TCIN\"},{\"name\":\"fields\",\"value\":\"product_hierarchies\"}],\"items\":[{\"identifier\":[{\"id_type\":\"DPCI\",\"id\":\"246-07-4198\",\"is_primary\":null,\"source\":\"Online and Store\"},{\"id_type\":\"TCIN\",\"id\":\"13860418\",\"is_primary\":null,\"source\":\"Online\"}],\"relation\":\"TAC\",\"relation_description\":\"Title Authority Child\",\"data_page_link\":\"http://www.target.com/p/the-rolling-stones-some-girls-live-in-texas-78-2-discs-dvd-cd/-/A-13860418\",\"imn_identifier\":17829228,\"is_orderable\":false,\"is_sellable\":true,\"general_description\":\"ONLINE ONLY  THE ROLLING STONES: S\",\"is_circular_publish\":true,\"business_process_status\":[{\"process_status\":{\"is_ready\":true,\"operation_description\":\"assortment ready\",\"operation_code\":\"PAAP\"}},{\"process_status\":{\"is_ready\":false,\"operation_description\":\"import ready\",\"operation_code\":\"PIPT\"}},{\"process_status\":{\"is_ready\":true,\"operation_description\":\"order ready\",\"operation_code\":\"PORD\"}},{\"process_status\":{\"is_ready\":true,\"operation_description\":\"presentation ready\",\"operation_code\":\"PPRS\"}},{\"process_status\":{\"is_ready\":true,\"operation_description\":\"project ready\",\"operation_code\":\"PCMT\"}},{\"process_status\":{\"is_ready\":true,\"operation_description\":\"replenishment ready\",\"operation_code\":\"PRPL\"}},{\"process_status\":{\"is_ready\":false,\"operation_description\":\"scale ready\",\"operation_code\":\"PSCL\"}},{\"process_status\":{\"is_ready\":true,\"operation_description\":\"target.com ready\",\"operation_code\":\"PTGT\"}}],\"dpci\":\"246-07-4198\",\"department_id\":246,\"class_id\":7,\"item_id\":4198,\"classification_hierarchy\":[{\"type_code\":\"prod\",\"node\":{\"id\":\"05\",\"description\":\"ELECTRONICS\",\"status_code\":\"ACTV\",\"node\":{\"id\":\"00E\",\"description\":\"Movies - MMBV\",\"status_code\":\"ACTV\",\"node\":{\"id\":\"0001\",\"description\":\"Movies\",\"status_code\":\"ACTV\",\"node\":{\"id\":\"01UJ\",\"description\":\"Movies\",\"status_code\":\"ACTV\"}}}}},{\"type_code\":\"merch\",\"node\":{\"id\":\"0003\",\"description\":\"HARDLINES\",\"node\":{\"id\":\"0013\",\"description\":\"ENTERTAINMENT\",\"node\":{\"id\":\"0022\",\"description\":\"ENTERTAINMENT\",\"node\":{\"id\":\"246\",\"description\":\"TCOM MOVIES\",\"status_code\":\"ACTV\",\"node\":{\"id\":\"07\",\"description\":\"CLASS 07\",\"status_code\":\"ACTV\",\"node\":{\"id\":\"0001\",\"description\":\"ECOMM\",\"status_code\":\"ACTV\",\"node\":{\"id\":\"TCI07\",\"description\":\"ECOMM\",\"status_code\":\"ACTV\"}}}}}}}},{\"type_code\":\"gpc\",\"node\":{\"id\":\"000000232\",\"description\":\"Audio Visual/Photography\",\"node\":{\"id\":\"000000232\",\"description\":\"Audio Visual Media\",\"node\":{\"id\":\"000001410\",\"description\":\"Pre-Recorded or Digital Content Media\",\"node\":{\"id\":\"000007179\",\"description\":\"DVD - Pre-recorded\"}}}}}]}]}}";  
	/*@Test
	public void contextLoads() {
	}*/

	 /* @Test
	public void testRequest() throws Exception {
		HttpHeaders headers = template.getForEntity("https://api.target.com/products/v3/13860428?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz", String.class).getHeaders();
		
		System.out.println("Out JSON String is"+headers.getLocation());
		//assertThat(headers.getLocation().toString(), containsString("myotherhost"));
	}*/
	
	/*
	@Test
    public void testSayHelloWorld() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }
	*/
	
	/*@Test
    public void test() {
        this.restTemplate.getForEntity(
            "/products/{userId}", String.class, "13860428");
    }*/
	
    /*
     * @Description: Unit Test function for My RestClient webservice Response JSON parser 
     * @return: success/Failure of Junit Test case
     */
	@Test
    public void responseJSONParser(){
    	ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    	try {
			ProductPricing productPricingTestJSON = mapper.readValue(productPricingTestJSONString, ProductPricing.class);
			System.out.println("Output JSOn String is "+productPricingTestJSON.getId());
			assertNotNull(productPricingTestJSON);
	        

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	} 	
    	
	/*
     * @Description: Unit Test function for API response JSON to POJO mapping 
     * @return: success/Failure of Junit Test case
     */
	@Test
        public void apiJSONResponse(){
        	ObjectMapper mapper = new ObjectMapper();
            //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        	try {
        		ProductApiPojo productApiPojoTestJSON = mapper.readValue(productApiPojoTestString, ProductApiPojo.class);
    			assertNotNull(productApiPojoTestJSON);
    	        

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
    }
	
}
