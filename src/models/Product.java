package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

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
	private ArrayList<LoaningPrice> loaningPrices = new ArrayList<>();
	private ArrayList<BulkDiscount> bulkDiscounts = new ArrayList<>();
	private LocalDateTime DATE_ADDED;
	private boolean enabled;
	

	/**
	 * Constructor of class Product
	 * @param productID of the product
	 * @param name of the product
	 * @param description of the product
	 * @param minStock of the product
	 * @param maxStock of the product
	 * 
	 * @exception IllegalArgumentException Min stock cannot be below zero!
	 */
	public Product(int id, String name, String description, int minStock, int maxStock, LocalDateTime dateAdded, boolean enabled) {
		this.ID = id;
		this.name = name;
		this.description = description;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.DATE_ADDED = dateAdded;
		this.enabled = enabled;
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

	/**
	 * Sets the min stock.
	 *
	 * @param minStock the new min stock
	 * 
	 * @exception IllegalArgumentException Min stock cannot be below zero!
	 */
	public void setMinStock(int minStock) {
		if (minStock < 0) {
			throw new IllegalArgumentException("Min stock cannot be below zero!");
		}
		this.minStock = minStock;
	}

	// set/get maxStock
	public int getMaxStock() {
		return this.maxStock;
	}

	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}

	public LocalDateTime getDateAdded() {
		return this.DATE_ADDED;
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
				latestSellingPrice = sellingPriceObj;
			}
		}
		return latestSellingPrice.getPrice();
	}
	
	/**
	 * adds selling price to an arraylist and the selling price is the latest one (current selling price)
	 * @param sellingPrice to be added to Arraylist
	 * @return true if successfully added
	 */
	public boolean addSellingPrice(SellingPrice sellingPrice) {
		return this.sellingPrices.add(sellingPrice);
	}


	/**
	 * Gets the current loaning price / hour
	 *
	 * @return the current loaning price / hour
	 */
	public BigDecimal getLatestLoaningPrice() {
		if (this.loaningPrices.isEmpty()) {
			return null;
		}

		LoaningPrice latestLoaningPrice = loaningPrices.get(0);
		LocalDateTime latestLoaningPriceDate = latestLoaningPrice.getDateAdded();
		for (LoaningPrice loaningPrice : this.loaningPrices) {
			if (loaningPrice.getDateAdded().isAfter(latestLoaningPriceDate)) {
				latestLoaningPrice = loaningPrice;
			}
		}
		return latestLoaningPrice.getPricePerHour();
	}
	/**
	 * adds loaning price to an arraylist, which becomes the latest (current) selling price
	 * @param loaningPrice to be added to an arraylist
	 * @return true if successfully added
	 */
	public boolean addLoaningPrice(LoaningPrice loaningPrice) {
		return this.loaningPrices.add(loaningPrice);
	}

	/**
	 * Adds the bulk discount.
	 *
	 * @param bulkDiscount the bulk discount
	 * @return true, if successful
	 */
	public boolean addBulkDiscount(BulkDiscount bulkDiscount) {
		return this.bulkDiscounts.add(bulkDiscount);
	}

	/**
	 * Gets the bulk discounts.
	 *
	 * @return the bulk discounts
	 */
	public ArrayList<BulkDiscount> getBulkDiscounts() {
		return this.bulkDiscounts;
	}
	
	/**
	 * Gets the active bulk discounts.
	 *
	 * @return the active bulk discounts
	 */
	public ArrayList<BulkDiscount> getActiveBulkDiscounts() {
    	ArrayList<BulkDiscount> activeBulkDiscounts = new ArrayList<>();
    	for (BulkDiscount bulkDiscount: this.getBulkDiscounts()) {
    		if (bulkDiscount.isActive()) {
    			activeBulkDiscounts.add(bulkDiscount);
    		}
    	}
    	return activeBulkDiscounts;
		
	}
	
	/**
	 * Gets the inactive bulk discounts.
	 *
	 * @return the inactive bulk discounts
	 */
	public ArrayList<BulkDiscount> getInactiveBulkDiscounts() {
    	ArrayList<BulkDiscount> inactiveBulkDiscounts = new ArrayList<>();
    	for (BulkDiscount bulkDiscount: this.getBulkDiscounts()) {
    		if (!bulkDiscount.isActive()) {
    			inactiveBulkDiscounts.add(bulkDiscount);
    		}
    	}
    	return inactiveBulkDiscounts;
	}
	
	/**
	 * Gets the best bulk discount.
	 *
	 * @param quantity the quantity
	 * @return the best bulk discount
	 */
	public BulkDiscount getBestBulkDiscount(int quantity) {
    	BulkDiscount bestBulkDiscount = null;
		for (BulkDiscount bulkDiscount: this.getActiveBulkDiscounts()) {
			if (quantity >= bulkDiscount.getMinQuantity()) {
				if (bestBulkDiscount == null) {
					bestBulkDiscount = bulkDiscount;
				} else {
					if (bulkDiscount.getDiscountPercentage() > bestBulkDiscount.getDiscountPercentage()) {
						bestBulkDiscount = bulkDiscount;
					}
				}
			}
		}
    	return bestBulkDiscount;
		
	}

	/**
<<<<<<< HEAD
	 * Gets the bulk discount by index in the list
=======
	 * Gets the bulk discount by index in arraylist (not ID)
>>>>>>> 5d62cd4 (Refactoring view)
	 *
	 * @param index the index
	 * @return the bulk discount by index
	 */
	public BulkDiscount getBulkDiscountByIndex(int index) {
		BulkDiscount bulkDiscountWithId = null;
		Iterator<BulkDiscount> it = this.getBulkDiscounts().iterator();
		while(it.hasNext()){
			BulkDiscount bulkDiscount = it.next();
			if(this.getBulkDiscounts().indexOf(bulkDiscount) == index) {
				bulkDiscountWithId = bulkDiscount;
			}
		}
		return bulkDiscountWithId;
	}

	/**
	 * Toggles BUlk Discount
	 *
	 * @param boolean active: true, inactive: false
	 */
	public void toggleBulkDiscount(BulkDiscount bulkDiscount) {
		bulkDiscount.setActive(!bulkDiscount.isActive());
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}