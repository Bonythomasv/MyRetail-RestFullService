package com.tgt.app.db;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    private String id;

    public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	private String productId;
    private double currentPrice;
    private String currencyCode;

    public Product() {}

    public Product(String productId, double currentPrice, String currencyCode) {
        this.productId = productId;
        this.currentPrice = currentPrice;
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, productId='%s', currentPrice='%s', currencyCode='%s']",
                id, productId, currentPrice, currencyCode);
    }

}
