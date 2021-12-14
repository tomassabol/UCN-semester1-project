package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.OrderLine;
import model.Product;
import model.SellingPrice;
import model.TrackableItem;

class OrderLineTest {
private OrderLine orderLine1;
private Product product1;
private Product product2;
private Product product3;
private TrackableItem trackableItem1;
private TrackableItem trackableItem2;
private Set<TrackableItem> trackableItems; 
private SellingPrice sellingPrice1;
	

	@BeforeEach
	void setUp() {
		 product1 = new Product(1, null, null, 0, 0, null);
		 product2 = new Product(2, null, null, 0, 0, null);
		 trackableItem1 = new TrackableItem(0, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE, product1);
		 trackableItem2 = new TrackableItem(1, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE, product2);
		 
				 
		trackableItems = new HashSet<TrackableItem>();
	}

	

	@Test
	void testMustContainOneItemAtleast() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			trackableItems.add(trackableItem1);
			orderLine1 = new OrderLine(null, 0, trackableItems, null, null);
				});
				
				assertEquals("The orderline must contain at least 1 item", thrown.getMessage());
			}
	//@Test
	void testAllItemsMustBeOfSameType() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			
			trackableItems.add(trackableItem1);
			trackableItems.add(trackableItem2);
			orderLine1 = new OrderLine(null, 0, trackableItems, null, null);
				});
				
				assertEquals("All items must be of same type(product)", thrown.getMessage());
			} 
	@Test
	void testSellinPriceCannotBeNull() {
		IllegalArgumentException thrown = 	assertThrows(IllegalArgumentException.class, () -> {
			trackableItems.add(trackableItem1);
			orderLine1 = new OrderLine(product1, 1, trackableItems, null, null);
				});
				
				assertEquals("Selling price cannot be null!", thrown.getMessage());
			}
}
	/*@Test
	void testProductPriceCannotBeBelowZero() {
		IllegalArgumentException thrown = 	assertThrows(IllegalArgumentException.class, () -> {
			//sellingPrice1 = new SellingPrice(BigDecimal.valueOf(-10), LocalDateTime.now());
			//product1.addSellingPrice(sellingPrice1);
			BigDecimal num = new BigDecimal("1");
			num = num.negate();
			orderLine1 = new OrderLine(product1, 1, null, num, null);
				});
				
				assertEquals("Product price cannot be below zero!", thrown.getMessage());
			} */
	/*@Test
	void testOrderLineMustContainAtleastOneItem() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			
			orderLine1 = new OrderLine(product1, 1, null, BigDecimal.valueOf(5), null);
				});
				
				assertEquals("An orderline must contain at least one item!", thrown.getMessage());
			} 
} */


