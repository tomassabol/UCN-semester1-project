package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ProductController;
import model.LoaningPrice;
import model.Product;
import model.ProductContainer;

class ProductControllerTest {

	private ProductContainer productCon;
	private Product product1;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() {
		productCon = ProductContainer.getInstance();
		
		product1 = new Product(12,"product1","description", 30, 40,LocalDateTime.now());
		
		
		
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateProduct() {
		ProductController productController = new ProductController();
		Product product = productController.createProduct("product1", "discreption", 10, 30);
		
		assertNotNull(product);
		assertEquals("discreption", product.getDescription());
	}
	@Test
	public	void testFindProductByID() {
		productCon.findProductByProductId(12);
		productCon.addProduct(product1);
		
		assertEquals(productCon.findProductByProductId(12), product1);
		assertNull(productCon.findProductByProductId(11));
			
		}
	@Test
	public void testCreateLoaningPrice() {
		
		LoaningPrice loaningPrice = new LoaningPrice(BigDecimal.valueOf(20), LocalDateTime.now());
		product1.addLoaningPrice(loaningPrice);
		
		assertNotNull(loaningPrice);
		
	}
	
		
		
			
}

