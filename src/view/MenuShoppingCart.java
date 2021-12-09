package view;

import model.Customer;
import model.Product;
import model.ShoppingCart;
import model.ShoppingItemLine;
import controller.ProductController;
import controller.ShoppingCartController;

public class MenuShoppingCart extends GenericMenuInterface{
	private static MenuShoppingCart instance;
    private Customer customer;

    private ProductController productCtrl;
    private ShoppingCartController shoppingCartCtrl;

    /**
     * Constructor for MenuUpdateProduct
     * @param product The product to update
     */
    private MenuShoppingCart(Customer customer) {
    	
        super();
        
        productCtrl = new ProductController();
        shoppingCartCtrl = new ShoppingCartController();
        this.customer = customer;
        
        this.setTitle("Shopping Cart Options");
        super.addMenuOption("1", new GenericMenuOption("Add item", () -> addItemToCart(customer.getShoppingCart())));
        super.addMenuOption("2", new GenericMenuOption("Create a quote from current shopping cart", () -> createQuoteFromCart()));
        super.addMenuOption("3", new GenericMenuOption("Clear shopping cart", () -> clearShoppingCart()));
        super.addMenuOption("0", new GenericMenuOption("Return to Product Menu", () -> MenuProduct.getInstace().show()));
        
    }
    
    public static MenuShoppingCart getInstance(Customer customer) {
    	if (instance == null) {
    		instance = new MenuShoppingCart(customer);
    	}
    	return instance;
    }
    
    @Override
    public void show() {
    	Terminal.getInstance().clearScreen();
    	
    	System.out.println("[Customer]");
    	System.out.println(String.format("Name: %s %s", 
    			customer.getFirstName(),
    			customer.getLastName()));
    	System.out.println("Birth: " + customer.getBirthDate());
		System.out.println();
		
		System.out.println("[Shopping cart]");
		this.printShoppingCart(customer.getShoppingCart());
		System.out.println();
    	super.show(false);
    }
    
    public void addItemToCart(ShoppingCart shoppingCart) {
    	Terminal.getInstance().clearScreen();
    	System.out.println("*** Products ***");
    	Terminal.getInstance().printBuyableProducts();
    	// TODO: need to create something like getBuyableProduct()
    	Product product = Terminal.getInstance().getProduct("Choose product by ID to add to cart");
    	int quantity = Terminal.getInstance().getIntegerInput("Quantity");
    	if (!shoppingCartCtrl.addProduct(shoppingCart, product, quantity)) {
    		System.out.println("The product could not be added to the cart!");
    	}
    	this.show();
    }
    
    public void createQuoteFromCart() {
    	
    }
    
    public void printShoppingCart(ShoppingCart shoppingCart) {
		for(ShoppingItemLine itemLine: shoppingCart.getItemLines()) {
			System.out.println(itemLine.getQuantity() + "x: "
					+ itemLine.PRODUCT.getName() 
					+ "\t" + itemLine.getCurrentPriceWithoutBulkDiscount() + " DKK");
			if (itemLine.getBulkDiscount() != null) {
				System.out.println(String.format("\t-%d%% bulk discount: %.2f DKK", 
						itemLine.getBulkDiscount().getDiscountPercentage(),
						itemLine.getCurrentPriceWithBulkDiscount()));
			}
		}
		System.out.println("-----");
		if (customer.getCustomerType().getDiscountPercentage() != 0) {
			System.out.println("-" + customer.getCustomerType().getDiscountPercentage() + "% customer discount");
		}
		System.out.println("Total: " + customer.getShoppingCart().calculateTotalPriceWithDiscountsApplied()+ " DKK");
    }
    
    public void clearShoppingCart() {
    	if (Terminal.getInstance().confirmInput("Are you sure you want to clear" + customer.getFirstName() + "'s cart?")) {
    		customer.getShoppingCart().clear();
    	}
    	this.show();
    }
    
}

