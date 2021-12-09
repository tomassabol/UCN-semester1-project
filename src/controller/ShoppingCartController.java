package controller;

import model.Product;
import model.ShoppingCart;
import model.ShoppingItemLine;

public class ShoppingCartController {

	public ShoppingCartController() {
	}
	

	public boolean addProduct(ShoppingCart shoppingCart, Product product, int quantity) {
		// TODO: Check that they are in stock
		ShoppingItemLine itemLine = new ShoppingItemLine(product, quantity);
		shoppingCart.add(itemLine);
		return true;
	}

}
