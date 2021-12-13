package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.BulkDiscount;
import model.LoaningPrice;
import model.PrimaryKey;
import model.Product;
import model.ProductContainer;
import model.SellingPrice;

public class ProductController {

	/**
	 * Constructor class ProductController
	 */
	public ProductController() {}

	/**
	 * @param name of the product to be created
	 * @param description of the product to be created
	 * @param minStock of the product to be created
	 * @param maxStock of the product to be created
	 * 
	 * @return The newly created Product object
	 */
	public Product createProduct(String name, String description, int minStock, int maxStock) {
		Product product = new Product(PrimaryKey.getNextProductID(), name, description,
				minStock, maxStock, LocalDateTime.now());
		ProductContainer.getInstance().addProduct(product);
		return product;
	}

	/**
	 * @param id of the product to be removed
	 */
	public void removeProduct(Product product) {
		ProductContainer.getInstance().removeProduct(product);
	}

	/**
	 * @param id of the product to be returned
	 * @return product with the specific ID
	 */
	public Product getProduct(int id) {
		return findProductByID(id);
	}

	/**
	 * @return list of all the products
	 */
	public List<Product> getProducts() {
		return ProductContainer.getInstance().getProducts();
	}
	
	/**
	 * @return list of all buyable products that have a selling price set.
	 */
	public List<Product> getBuyableProducts() {
		return ProductContainer.getInstance().getBuyableProducts();
	}

	// Update product methods
	/**
	 * @param id of the product to be updated
	 * @param name - new name of the product that will overwrite the previous one
	 */
	public void updateProductName(Product product, String name) {
		product.setName(name);
	}

	/**
	 * @param id of the product to be updated
	 * @param description - new description of the product that will overwrite the previous one
	 */
	public void updateProductDescription(Product product, String description) {
		product.setDescription(description);
	}

	/**
	 * @param id of the product to be updated
	 * @param minStock - new minimal stock of the product that will overwrite the previous one
	 */
	public void updateProductMinStock(Product product, int minStock) {
		product.setMinStock(minStock);
	}

	/**
	 * @param id of the product to be updated
	 * @param maxStock - new maximal stock of the product that will overwrite the previous one
	 */
	public void updateProductMaxStock(Product product, int maxStock) {
		product.setMaxStock(maxStock);
	}
	
	/**
	 * @param id of the product to be found
	 * @return product
	 */
	public Product findProductByID(int id) {
		return ProductContainer.getInstance().findProductById(id);
	}
	
	public Product findBuyableProductByID(int id) {
		return ProductContainer.getInstance().findBuyableProductById(id);
	}

	/**
	 * creates a selling price, which is also the latest selling price
	 * @param price - new price entered by user
	 * @param product - product for which is this new price set
	 * @return selling price entered by user
	 */
	public SellingPrice createSellingPrice(BigDecimal price, Product product) {
		SellingPrice sellingPrice = new SellingPrice(price, LocalDateTime.now());
		product.addSellingPrice(sellingPrice);
		return sellingPrice;
	}

	/**
	 * creates new loaning price, which becomes the latest loaning price for the product
	 * @param price new price entered by user
	 * @param product - for which is this new price set
	 * @return loaning price created by user
	 */
	public LoaningPrice createLoaningPrice(BigDecimal price, Product product) {
		LoaningPrice loaningPrice = new LoaningPrice(price, LocalDateTime.now());
		product.addLoaningPrice(loaningPrice);
		return loaningPrice;
	}
<<<<<<< HEAD

	/**
	 * 
	 * @param product The product whose stock gets checked
	 * @return the quantity that is in stock
	 */
	public int getStock(Product product) {
		StockController stockController = new StockController();
		int quantity = 0;
		if(stockController.buyableItemQuantityInStock(product) != 0) {
			quantity = stockController.buyableItemQuantityInStock(product);
		}
		return quantity;
	}
	
	/**
	 * Gets all bulk discounts
	 *
	 * @param product the product
	 * @return the bulk discounts
	 */
	public ArrayList<BulkDiscount> getBulkDiscounts(Product product) {
		return product.getBulkDiscounts();
	}
	
	/**
	 * Gets the bulk discount by index in list
	 *
	 * @param product the product
	 * @param index the index
	 * @return the bulk discount by index
	 */
	public BulkDiscount getBulkDiscountByIndex(Product product, int index) {
		return product.getBulkDiscountByIndex(index);
	}
	
	/**
	 * Gets the inactive bulk discounts.
	 *
	 * @param product the product
	 * @return the inactive bulk discounts
	 */
	public ArrayList<BulkDiscount> getInactiveBulkDiscounts(Product product) {
		return product.getInactiveBulkDiscounts();
	}
	
	/**
	 * Gets the active bulk discounts.
	 *
	 * @param product the product
	 * @return the active bulk discounts
	 */
	public ArrayList<BulkDiscount> getActiveBulkDiscounts(Product product) {
		return product.getActiveBulkDiscounts();
	}
	
	
	
	/**
	 * Sets the bulk discount status.
	 *
	 * @param bulkDiscount the bulk discount
	 * @param status active: true, inactive: false
	 */
	public void setBulkDiscountStatus(BulkDiscount bulkDiscount, boolean status) {
		bulkDiscount.setActive(status);
	}
	
	/**
	 * Creates the bulk discount and adds it to the product
	 *
	 * @param product the product
	 * @param minQuantity the min quantity for it to apply
	 * @param discountPercentage the discount percentage
	 * @return true, if successful
	 */
	public boolean createBulkDiscount(Product product, int minQuantity, int discountPercentage) {
		BulkDiscount bulkDiscount = new BulkDiscount(minQuantity, discountPercentage);
=======
	
	/**
	 * Adds the bulk discount to a product
	 *
	 * @param product the product
	 * @param bulkDiscount the bulk discount
	 * @return true, if successful
	 */
	public boolean addBulkDiscount(Product product, BulkDiscount bulkDiscount) {
>>>>>>> 5d62cd4 (Refactoring view)
		return product.addBulkDiscount(bulkDiscount);
	}
}
