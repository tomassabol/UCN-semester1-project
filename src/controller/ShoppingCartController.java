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
	 * Note: it checks the quantity & updates quantity if the item is already in cart
	 *
	 * @param shoppingCart the shopping cart
	 * @param product the product
	 * @param quantity the quantity
	 * @return true, if successful
	 * 
	 * @exception IllegalArgumentException when quantity <= 0
	 * @exception IllegalArgumentException When adding product that doesn't have a buy price
	 */
	public ShoppingItemLine addProduct(ShoppingCart shoppingCart, Product product, int quantity)  throws OutOfStockException  {
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
			return alreadyInCart;
		// not in cart
		} else {
			ShoppingItemLine itemLine;
			// in stock
			if (quantity <= buyableQuantityInStock) {
				itemLine = new ShoppingItemLine(product, quantity);
				shoppingCart.add(itemLine);
			// not in stock
			} else {
				throw new OutOfStockException(String.format("Could not add %d item(s) to cart as there are only %d in stock", 
						quantity,
						buyableQuantityInStock));
			}
			return itemLine;
		} 
		

	}
	
	/**
	 * Clear shopping cart.
	 *
	 * @param shoppingCart the shopping cart
	 */
	public void clearCart(ShoppingCart shoppingCart) {
		shoppingCart.clear();
	}
	
	/**
	 * Removes an itemLine from the shopping cart.
	 *
	 * @param shoppingCart the shopping cart
	 * @param itemLine the item line
	 */
	public void remove(ShoppingCart shoppingCart, ShoppingItemLine itemLine) {
		shoppingCart.remove(itemLine);
	}
	
	/**
	 * Update an item line's quantity
	 *
	 * @param itemLine the item line
	 * @throws OutOfStockException 
	 * @throws IllegalArgumentException when quantity <= 0
	 */
	public void updateQuantity(ShoppingItemLine itemLine, int quantity) throws OutOfStockException {
		// if quantity < 0, throw exception
		if (quantity < 1) {
			throw new IllegalArgumentException("The quantity of the product must be a positive number!");
		}
		
		// if quantity > stock, throw exception
		int buyableQuantityInStock = Stock.getInstance().getBuyableQuantityInStock(itemLine.getPRODUCT());
		if (quantity > buyableQuantityInStock) {
			throw new OutOfStockException(String.format("Could not add %d item(s) to cart as there are only %d in stock", 
					quantity,
					buyableQuantityInStock));
		}
		
		// Set quantity
		itemLine.setQuantity(quantity);
	}
	

}
