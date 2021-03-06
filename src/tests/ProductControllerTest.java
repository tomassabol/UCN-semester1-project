package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ProductController;
import models.LoaningPrice;
import models.Product;
import models.containers.ProductContainer;

class ProductControllerTest {

	private ProductContainer productCon;
	private Product product1;
	private Product product2;



	@BeforeEach
	public void setUp() {
		productCon = ProductContainer.getInstance();
		
		product1 = new Product(12,"product1","description", 30, 40,LocalDateTime.now());
		product2 = new Product(12,"product2","description2", 20, 70,LocalDateTime.now());
		
		
		
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
		productCon.findProductById(12);
		productCon.addProduct(product1);
		
		assertEquals(productCon.findProductById(12), product1);
		assertNull(productCon.findProductById(11));
			
		}
	@Test
	public void testCreateLoaningPrice() {
		
		LoaningPrice loaningPrice = new LoaningPrice(BigDecimal.valueOf(6500), LocalDateTime.now());
		product1.addLoaningPrice(loaningPrice);
		assertNotNull(loaningPrice);
		
	}
	@Test
	public void testLoanPriceSholdNotBeLessThanZero() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			LoaningPrice loaningPrice = new LoaningPrice(BigDecimal.valueOf(-10), LocalDateTime.now());
			product2.addLoaningPrice(loaningPrice);
		});
		assertEquals("Price per hour shouldn't be below 0", thrown.getMessage());
		
	}	
			
}

