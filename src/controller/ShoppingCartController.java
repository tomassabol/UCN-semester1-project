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
		int buyableQuantityInStock = Stock.getInstance().getBuyableQuantityInStock(product);
		if (buyableQuantityInStock >= quantity) {
			ShoppingItemLine itemLine = new ShoppingItemLine(product, quantity);
			shoppingCart.add(itemLine);
		} else {
			throw new OutOfStockException(String.format("Could not add %d item(s) to cart as there are only %d in stock", 
					quantity,
					buyableQuantityInStock));
		}

	}

}
