package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ShoppingCartController;
import exception.OutOfStockException;
import model.Customer;
import model.IFCustomer;
import model.Product;
import model.SellingPrice;
import model.Shelf;
import model.ShoppingCart;
import model.StockBatch;
import model.StorageLocation;
import model.TrackableItem;
import model.container.StockContainer;

class ShoppingCartControllerTest {
	
	private IFCustomer customer1;
	private Product product1;
	private ShoppingCart shoppingCart1;
	private ShoppingCartController shoppingCartCtrl;
	private TrackableItem trakableItem1;
	private StockBatch stockBatch1;
	private StorageLocation storageLocation1;
	private Shelf shelf1;
	private SellingPrice sellingPrice1;
	private Product product2;
	

	@BeforeEach
	void setUp()  {
		shoppingCartCtrl = new ShoppingCartController();
		storageLocation1 = new StorageLocation(1, null, null, false);
		shelf1 = new Shelf(1, null, storageLocation1);
		customer1 = new Customer(5, null, null, null, null, null, null);
		product1 = new Product(3, "Test", "Test", 1, 40, null);
		sellingPrice1 = new SellingPrice(BigDecimal.valueOf(200), LocalDateTime.now());
		stockBatch1 = new StockBatch(product1, 10);
		product2 = new Product(3, "Test", "Test", 1, 40, null);
		
		
		
		
	}

	

	@Test
	void testAddProductToShoppingCart() throws OutOfStockException {
		StockContainer.getInstance().addStorageLocation(storageLocation1);
		StockContainer.getInstance().addShelf(storageLocation1, shelf1);
		product1.addSellingPrice(sellingPrice1);
		shelf1.addStockBatch(product1, stockBatch1);
		shoppingCartCtrl.addProduct(customer1.getShoppingCart(), product1, 1);
		
		assertEquals(false, customer1.getShoppingCart().isEmpty());	
	}
	@Test
	void testCaddItemLineToaShoppingCartWhichIsDoesNotHaveABuyPrice() {
	IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
		
		StockContainer.getInstance().addStorageLocation(storageLocation1);
		StockContainer.getInstance().addShelf(storageLocation1, shelf1);
		shelf1.addStockBatch(product2, stockBatch1);
		shoppingCartCtrl.addProduct(customer1.getShoppingCart(), product2, 1);
			});
			
			assertEquals("Cannot add itemLine to a shopping cart which doesn't have a buy price!", thrown.getMessage());
		}
	
		
	}


