package model;

/**
 * @authors tomassabol, danielskenepe, tamastoth, attilabako
 * @version Nov 30, 2021
 */

public class Product {
	private int productID;
	private String name;
	private String description;
	private int minStock;
	private int maxStock; 
	/**
	 * Constructor class Product
	 */
	public Product(int productID, String name, String description, int minStock, int maxStock) {
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.minStock = minStock;
		this.maxStock = maxStock;
	}

	// set/get productID
	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
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

}
