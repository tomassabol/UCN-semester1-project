package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.DisabledStateException;
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
	 * Adds a product with specific quantity to a shopping cart
	 * Note: if the product is already in cart, the quantity will be added to the existing item line.
	 *
	 * @param shoppingCart the shopping cart
	 * @param product the product
	 * @param quantity the quantity
	 * 
	 * @return ShoppingItemLine either the newly created one,
	 * 				or the one that was incremented (if already in cart)
	 * 
	 * @exception IllegalArgumentException when quantity <= 0, and when product or shoppingCart is null
	 * @exception NullPriceEception if a product's buy price is null
	 * @exception DisabledStateException if a product is disabled
	 */
	public ShoppingItemLine addProduct(ShoppingCart shoppingCart, Product product, int quantity)
			throws OutOfStockException, NullPriceException, DisabledStateException  {
		// Validation
		if (shoppingCart == null || product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		
		// A product must have a buy price
		if (product.getLatestSellingPrice() == null) {
			throw new NullPriceException("Cannot add a product to cart with no (buy) price!");
		}
		
		// A product must not be disabled
		if (!product.isEnabled()) {
			throw new DisabledStateException("Cannot add a disabled product to a shopping cart!");
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
	
	// Removes items that cannot be bought from cart and returns them.
	// Unbuyable means out of stock, disabled, or ones with no 'buy' price
	
	
	/**
	 * 	// Removes items that cannot be bought from cart and returns them.
	 * 	// Unbuyable means out of stock, disabled, or ones with no 'buy' price
	 *
	 * @requires StockController
	 *
	 * @param shoppingCart the shopping cart
	 * @return A list of removed ShoppingItemLine's
	 */
	public List<ShoppingItemLine> removeUnbuyableItems(ShoppingCart shoppingCart) {
		// initialize Stock Controller
		StockController stockCtrl = new StockController();
		
		// Keep track of item lines to remove
		List<ShoppingItemLine> removedItemLines = new ArrayList<>();
		
		Iterator<ShoppingItemLine> itemLines = shoppingCart.getItemLines().iterator();
		while (itemLines.hasNext()) {
			ShoppingItemLine itemLine = itemLines.next();
			
			// if no price
			if (itemLine.getPRODUCT().getLatestSellingPrice() == null) {
				itemLines.remove();
				removedItemLines.add(itemLine);
			}
			
			// if disabled
			if (!itemLine.getPRODUCT().isEnabled()) {
				itemLines.remove();
				removedItemLines.add(itemLine);
			}
			
			// if available quantity < required: 
					// set to quantity IN STOCK,
					// or remove if 'in stock quantity' <= 0
			int quantityInStock = stockCtrl.getBuyableQuantityInStock(itemLine.getPRODUCT());
			if (quantityInStock <= 0) {
				itemLines.remove();
				removedItemLines.add(itemLine);
			}
			
		}
		
		// return removed item lines
		return removedItemLines;
	}
	
	/**
	 * Sets item lines' quantity to available quantity if stock has less than the required item amount
	 * 
	 * @requires StockController
	 *
	 * @param shoppingCart the shopping cart
	 * @return A list of removed ShoppingItemLine's
	 */
	public List<ShoppingItemLine> adjustQuantity(ShoppingCart shoppingCart) {
		// initialize Stock Controller
		StockController stockCtrl = new StockController();
		
		// Keep track of item lines to remove
		List<ShoppingItemLine> adjustedItemLines = new ArrayList<>();
		
		Iterator<ShoppingItemLine> itemLines = shoppingCart.getItemLines().iterator();
		while (itemLines.hasNext()) {
			ShoppingItemLine itemLine = itemLines.next();
			
			int quantityInStock = stockCtrl.getBuyableQuantityInStock(itemLine.getPRODUCT());
			if (itemLine.getQuantity() > quantityInStock && quantityInStock > 0) {
				itemLine.setQuantity(quantityInStock);
				adjustedItemLines.add(itemLine);
			}
		}
			
		// Return adjusted item lines
		return adjustedItemLines;
	}
	

}
