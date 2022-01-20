package controller;

import exceptions.NullPriceException;
import exceptions.OutOfStockException;
import model.Product;
import model.ShoppingCart;
import model.ShoppingItemLine;
import model.Stock;

public class ShoppingCartController {

	public ShoppingCartController() {
	}
	

	/**
	 * Adds the product to a shopping cart
	 * Note: it checks the quantity and adds to it if the item is already in cart
	 *
	 * @param shoppingCart the shopping cart
	 * @param product the product
	 * @param quantity the quantity
	 * @return true, if successful
	 * 
	 * @exception IllegalArgumentException when quantity <= 0, and when product or shoppingCart is null
	 * @exception NullPriceEception When a product's buy price is null
	 */
	public ShoppingItemLine addProduct(ShoppingCart shoppingCart, Product product, int quantity)  throws OutOfStockException, NullPriceException  {
		// Validation
		if (shoppingCart == null || product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		
		// A product must have a buy price
		if (product.getLatestSellingPrice() == null) {
			throw new NullPriceException("Cannot add a product to cart with no (buy) price!");
		}
		
		
		// if product already in cart, store the itemLine
		ShoppingItemLine alreadyInCart = null;
		
		for (ShoppingItemLine itemLine: shoppingCart.getItemLines()) {
			if (itemLine.getPRODUCT() == product) {
				alreadyInCart = itemLine;
			}
		}
		// get avalable buyable quantity in stock
		int buyableQuantityInStock = Stock.getInstance().getBuyableQuantityInStock(product);
		
		// if the product is already in cart
		if (alreadyInCart != null) {
			// if requested quantity plus the quantity already in cart are in stock:
				// increment item line's quantity
			if ((quantity + alreadyInCart.getQuantity()) <= buyableQuantityInStock) {
				alreadyInCart.addItems(quantity);
			// if requested quantity plus the quantity already in cart NOT in stock:
				// exception
			} else {
				throw new OutOfStockException(String.format("Could not add %d item(s) to cart as you already have %d in cart and there are only %d in stock", 
						quantity,
						alreadyInCart.getQuantity(),
						buyableQuantityInStock));
			}
			return alreadyInCart;
		// If the product is not already in cart
		} else {
			ShoppingItemLine itemLine;
			// Add to shopping cart if available stock covers it
			if (quantity <= buyableQuantityInStock) {
				itemLine = new ShoppingItemLine(product, quantity);
				shoppingCart.add(itemLine);
			// if not enough stock, throw exception
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
