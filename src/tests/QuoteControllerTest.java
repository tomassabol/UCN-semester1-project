package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.QuoteController;
import model.Customer;
import model.CustomerType;
import model.Employee;
import model.PrimaryKey;
import model.Quote;
import model.QuoteContainer;

class QuoteControllerTest {
	private QuoteContainer quoteCon;
	private Quote quote1;
	private Customer customer1;
	private Employee employee1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		quoteCon = QuoteContainer.getInstance();
		CustomerType customerType = new CustomerType(PrimaryKey.getNextCustomerTypeID(), "Normal", 5);
		customer1 = new Customer(0, null, null, null, null, customerType, null);
		employee1 = new Employee(0, null, null, null, null, null, null);
		quote1 = new Quote(1, customer1, employee1, null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindQuoteById() {
	quoteCon.findQuoteByID(1);
	quoteCon.addQuote(quote1);
	
	assertEquals(quoteCon.findQuoteByID(1), quote1);
	
	}
	@Test
	void testCreateQoute() {
		QuoteController quoteCtrl = new QuoteController();
		boolean quoteCreated = quoteCtrl.createQuote(customer1, employee1, customer1.getShoppingCart());
		
	assertTrue(quoteCreated);
	}
}
