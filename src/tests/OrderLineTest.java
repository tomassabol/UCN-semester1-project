package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.OrderLine;
import models.Product;
import models.TrackableItem;

class OrderLineTest {
private OrderLine orderLine1;
private Product product1;


private TrackableItem trackableItem1;

private Set<TrackableItem> trackableItems; 

	

	@BeforeEach
	void setUp() {
		 product1 = new Product(0, null, null, 0, 0, null);
		 
		
		 trackableItem1 = new TrackableItem(0, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE, product1);
		 
				 
		 trackableItems = new HashSet<TrackableItem>();
	}

	

	

	@Test
	void testSellinPriceCannotBeNull() {
		IllegalArgumentException thrown = 	assertThrows(IllegalArgumentException.class, () -> {
			
			orderLine1 = new OrderLine(product1, 1, trackableItems, null, null);
				});
				
				assertEquals("Selling price cannot be null!", thrown.getMessage());
			}
	
} 


