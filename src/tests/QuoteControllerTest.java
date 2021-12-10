package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.PrimaryKey;
import model.Quote;
import model.QuoteContainer;

class QuoteControllerTest {
	private QuoteContainer quoteCon;
	private Quote quote1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		quoteCon = QuoteContainer.getInstance();
		quote1 = new Quote(1, "Customer John", "Employye Ben", "VIP",5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindCustomerById() {
	
	}

}
