package tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Product;
import model.SupplyOffer;
import model.SupplyOfferContainer;
import model.TrackableItem;

class SupplyControllerTest {
private SupplyOfferContainer supplyCon;
private SupplyOffer supplyOffer1;
private Product product1;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() {
		supplyCon = SupplyOfferContainer.getInstance();
		supplyOffer1 = new SupplyOffer(0, null, 0, null, false, null);
		product1 = new Product(1,"product1", "discreption", 10, 30, null);
		
	}

	@AfterEach
	public void tearDown()  {
	}
	
	
	@Test
	public void testFindSupplyOfferById() {
		supplyCon.findSupplyOfferById(1);
		supplyCon.addSupplyOffer(product1, supplyOffer1);
		
		assertEquals(supplyCon.findSupplyOfferById(1), supplyOffer1);
		//assertNull(supplyCon.findSupplyOfferById(product1, 0));
		
	}
	
	@Test
	public void testUpdate() {
		
	}

}



/*
@Test 
public void testCreateSupplyOffer() {
	SupplyController SupplyController = new SupplyController();
	Supply supply = SupplyController.createSupplyOffer(1, 20, 500, "product1", "Tree Build", true, LocalDateTime.now());
}
*/