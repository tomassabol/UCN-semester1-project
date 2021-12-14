package controller;

import model.Product;
import model.ShoppingCart;
import model.ShoppingItemLine;
import model.Stock;
import model.OutOfStockException;

public class ShoppingCartController {

	public ShoppingCartController() {
	}
	

	/**
	 * Adds the product to a shopping cart
	 *
	 * @param shoppingCart the shopping cart
	 * @param product the product
	 * @param quantity the quantity
	 * @return true, if successful
	 * 
	 * @exception IllegalArgumentException when quantity <= 0
	 * @exception IllegalArgumentException When adding product that doesn't have a buy price
	 */
	public void addProduct(ShoppingCart shoppingCart, Product product, int quantity)  throws OutOfStockException  {
		// check if product already is in cart
		ShoppingItemLine alreadyInCart = null;
		for (ShoppingItemLine itemLine: shoppingCart.getItemLines()) {
			if (itemLine.getPRODUCT() == product) {
				alreadyInCart = itemLine;
			}
		}
		// get quantity in stock
		int buyableQuantityInStock = Stock.getInstance().getBuyableQuantityInStock(product);
		
		// In cart
		if (alreadyInCart != null) {
			// already in cart + quantity in cart are in stock
			if ((quantity + alreadyInCart.getQuantity()) <= buyableQuantityInStock) {
				alreadyInCart.addItems(quantity);
			// not in stock
			} else {
				throw new OutOfStockException(String.format("Could not add %d item(s) to cart as you already have %d in cart and there are only %d in stock", 
						quantity,
						alreadyInCart.getQuantity(),
						buyableQuantityInStock));
			}
		// not in cart
		} else {
			// in stock
			if (quantity <= buyableQuantityInStock) {
				ShoppingItemLine itemLine = new ShoppingItemLine(product, quantity);
				shoppingCart.add(itemLine);
			// not in stock
			} else {
				throw new OutOfStockException(String.format("Could not add %d item(s) to cart as there are only %d in stock", 
						quantity,
						buyableQuantityInStock));
			}
		} 
		

	}

}
