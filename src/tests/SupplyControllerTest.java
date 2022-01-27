package tests;



import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Product;
import model.SupplyOffer;
import model.container.ProductContainer;
import model.container.SupplyOfferContainer;

class SupplyControllerTest {
private SupplyOfferContainer supplyCon;
private ProductContainer productCon;
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
		productCon = ProductContainer.getInstance();
		supplyOffer1 = new SupplyOffer(1, null, 0, null, false, null);
		product1 = new Product(1,"product1", "discreption", 10, 30, null);
		
	}

	@AfterEach
	public void tearDown()  {
	}
	
	
	@Test
	public void testFindSupplyOfferById() {
		supplyCon.addSupplyOffer(product1, supplyOffer1);
		productCon.addProduct(product1);
		supplyCon.findSupplyOfferById(1);
		
		
		assertEquals(supplyCon.findSupplyOfferById(1), supplyOffer1);
		//assertNull(supplyCon.findSupplyOfferById(product1, 0));
		
	}
	
}






