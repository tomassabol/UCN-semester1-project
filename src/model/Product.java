package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @authors tomassabol, danielskenepe, tamastoth, attilabako
 * @version Nov 30, 2021
 */

public class Product {
	public final int ID;
	private String name;
	private String description;
	private int minStock;
	private int maxStock; 
	private ArrayList<SellingPrice> sellingPrices = new ArrayList<>();
	private BulkDiscount bulkDiscount = null;
	private LoaningPrice loaningPrice = null;
	private LocalDateTime DATE_ADDED = LocalDateTime.now();
	
	/**
	 * Constructor of class Product
	 * @param productID of the product
	 * @param name of the product
	 * @param description of the product
	 * @param minStock of the product
	 * @param maxStock of the product
	 * TODO: Overload container with sellingPrice, loaningprice. 
	 * TODO: add logic for if none (sellingPrice or LoaningPrice) is set.
	 */
	public Product(int id, String name, String description, int minStock, int maxStock, LocalDateTime dateAdded) {
		this.ID = id;
		this.name = name;
		this.description = description;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.DATE_ADDED = dateAdded;
	}

	// set/get name
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// set/get description
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// set/get minStock
	public int getMinStock() {
		return this.minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	// set/get maxStock
	public int getMaxStock() {
		return this.maxStock;
	}

	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}

	/**
	 *  this methods checks the latest price of the product. 
	 *  You cannot change the price, only add a new one. 
	 *  This is useful for later to generate stats
	 * @return price of the product that was added latests
	 */
	public BigDecimal getLatestSellingPrice() {
		if (this.sellingPrices.isEmpty()) {
			return null;
		}
		
		SellingPrice latestSellingPrice = sellingPrices.get(0); 
		LocalDateTime latestSellingPriceDate = latestSellingPrice.getDateAdded();
		for(SellingPrice sellingPriceObj: this.sellingPrices) {
			if (sellingPriceObj.getDateAdded().isAfter(latestSellingPriceDate)) {
				latestSellingPriceDate = sellingPriceObj.getDateAdded();
			}
		}
		return latestSellingPrice.getPrice();
	}
	
	public boolean addSellingPrice(SellingPrice sellingPrice) {
		// if below 0, return False
		if (sellingPrice.getPrice().compareTo(BigDecimal.ZERO) == -1) {
			return false;
		}
		return this.sellingPrices.add(sellingPrice);
	}

	public void printProductInfo() {
		System.out.println("Product ID: " + ID + "\nProduct name: " + name + "\nProduct description: " + description + "\nMinimal Stock: " + minStock + "\nMaximal stock: "+ maxStock);
	}

}