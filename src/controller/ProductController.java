package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
	public void removeProduct(int id) {
		Product product = findProductByID(id);
		ProductContainer.getInstance().removeProduct(product);
	}

	/**
	 * @param id of the product to be returned
	 * @return product with the specific ID
	 */
	public Product getProduct(int id) {
		Product product = findProductByID(id);
		return product;
	}

	/**
	 * @return list of all the products
	 */
	public List<Product> getProducts() {
		return ProductContainer.getInstance().getProducts();
	}

	// Update product methods
	/**
	 * @param id of the product to be updated
	 * @param name - new name of the product that will overwrite the previous one
	 */
	public void updateProductName(int id, String name) {
		Product product = findProductByID(id);
		product.setName(name);
	}

	/**
	 * @param id of the product to be updated
	 * @param description - new description of the product that will overwrite the previous one
	 */
	public void updateProductDescription(int id, String description) {
		Product product = findProductByID(id);
		product.setDescription(description);
	}

	/**
	 * @param id of the product to be updated
	 * @param minStock - new minimal stock of the product that will overwrite the previous one
	 */
	public void updateProductMinStock(int id, int minStock) {
		Product product = findProductByID(id);
		product.setMinStock(minStock);
	}

	/**
	 * @param id of the product to be updated
	 * @param maxStock - new maximal stock of the product that will overwrite the previous one
	 */
	public void updateProductMaxStock(int id, int maxStock) {
		Product product = findProductByID(id);
		product.setMaxStock(maxStock);
	}
	
	/**
	 * @param id of the product to be found
	 * @return product
	 */
	public Product findProductByID(int id) {
		return ProductContainer.getInstance().findProductByProductId(id);
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

	/**
	 * Print all products
	 */
	public void getProductInfo(){
		ProductContainer pContainer = ProductContainer.getInstance();
		for (Product product : pContainer.getProducts()) {
			product.getProductInfo();
		}
	}
}
