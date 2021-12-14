package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Product;
import model.StockBatch;
import model.TrackableItem;

class StockBatchTest {
private StockBatch stockBatch1;
private TrackableItem trackableItem1;
private Product product1;
private Set<TrackableItem> trackableItems;



	@BeforeEach
	void setUp() throws Exception {
		product1 = new Product(3, "Test", "Test", 1, 40, null);
		trackableItem1 = new TrackableItem(1, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE, product1);
		trackableItems = new HashSet<TrackableItem>();
		
	}

	

	@Test
	void testIfContainAtleatOneProduct() {
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			//trackableItems.add(trackableItem1);
			stockBatch1 = new StockBatch(trackableItems);
		});
		
		
		assertEquals("Must contain at least 1 product", thrown.getMessage());
	}

}
