
package com.tgt.response;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "value",
    "currency_code"
})
public class CurrentPrice {

    @JsonProperty("value")
    private Double value;
    @JsonProperty("currency_code")
    private String currencyCode;
    

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentPrice() {
    }

    /**
     * 
     * @param currencyCode
     * @param value
     */
    public CurrentPrice(Double value, String currencyCode) {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currency_code
     */
    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

   

}
