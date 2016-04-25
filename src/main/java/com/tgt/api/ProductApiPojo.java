
package com.tgt.api;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "product_composite_response"
})
public class ProductApiPojo {

    @JsonProperty("product_composite_response")
    private ProductCompositeResponse productCompositeResponse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The productCompositeResponse
     */
    @JsonProperty("product_composite_response")
    public ProductCompositeResponse getProductCompositeResponse() {
        return productCompositeResponse;
    }

    /**
     * 
     * @param productCompositeResponse
     *     The product_composite_response
     */
    @JsonProperty("product_composite_response")
    public void setProductCompositeResponse(ProductCompositeResponse productCompositeResponse) {
        this.productCompositeResponse = productCompositeResponse;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
