package controller;

import model.Product;
import model.ProductContainer;

public class ProductController {

	public ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	public Product findProductByID(int id) {
		return ProductContainer.getInstance().findProductByProductId(id);
	}

}
