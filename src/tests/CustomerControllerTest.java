package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.CustomerContainer;

class CustomerControllerTest {
	private CustomerContainer customerCon;
	private Customer customer1;
	private Customer customer2;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() {
		customerCon = CustomerContainer.getInstance();
		customer1 = new Customer(0, "John", "Nerd", null, null, null, null);
		customer2 = new Customer(5, "Elon", "Mask", null, null, null, null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindCustomerById() {
		customerCon.findCustomerById(0);
		customerCon.addCustomer(customer1);
		
		assertEquals(customerCon.findCustomerById(0), customer1);
		assertNotEquals(customerCon.findCustomerById(0), customer2);
		
	}

}