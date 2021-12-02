package model;

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
	private int untrackableItemStock;
	
	/**
	 * Constructor of class Product
	 * @param productID of the product
	 * @param name of the product
	 * @param description of the product
	 * @param minStock of the product
	 * @param maxStock of the product
	 * @param UntrackableItemStock - if item is untrackable(does not have serial number), how many are in stock
	 */
	public Product(int id, String name, String description, int minStock, int maxStock, int untrackableItemStock) {
		this.ID = id;
		this.name = name;
		this.description = description;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.untrackableItemStock = untrackableItemStock;
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

	// set/get maxStock
	public int getUntrackableItemStock() {
		return this.untrackableItemStock;
	}

	public void setUntrackableItemStock(int untrackableItemStock) {
		this.untrackableItemStock = untrackableItemStock;
	}

}
