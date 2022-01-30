package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Product;
import models.ShoppingItemLine;

class ShoppingItemLineTest {
private ShoppingItemLine shoppingItemLine1;
private Product product1;

	

	@BeforeEach
	void setUp() {
		product1 = new Product(1, null, null, 0, 0, null);
	}
	
	

	@Test
	void test() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			
			shoppingItemLine1 = new ShoppingItemLine(product1, -2);
		});
		
		assertEquals("Quantity cannot be less than or equal to 0!", thrown.getMessage());
	}

}
