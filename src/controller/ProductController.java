package controller;

import java.util.List;
import model.PrimaryKey;
import model.Product;
import model.ProductContainer;

public class ProductController {

	/**
	 * Constructor class ProductController
	 */
	public ProductController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name of the product to be created
	 * @param description of the product to be created
	 * @param minStock of the product to be created
	 * @param maxStock of the product to be created
	 */
	public void createProduct(String name, String description, int minStock, int maxStock) {
		Product product = new Product(PrimaryKey.getNextProductID(), name, description, minStock, maxStock);
		ProductContainer.getInstance().addProduct(product);
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
	
	public Product findProductByID(int id) {
		return ProductContainer.getInstance().findProductByProductId(id);
	}

}
