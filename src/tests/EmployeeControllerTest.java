package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Employee;
import model.EmployeeContainer;

class EmployeeControllerTest {
	private EmployeeContainer employeeCon;
	private Employee employee1;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp()  {
		employeeCon = EmployeeContainer.getInstance();
		
		employee1 = new Employee(1, "081090-3221", "Tomas", "Sabol", "AleneGade", "40504432",null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindEmployeeById() {
		employeeCon.findEmployeeById(1);
		employeeCon.addEmployee(employee1);
		
		assertEquals(employeeCon.findEmployeeById(1), employee1);
		assertNull(employeeCon.findEmployeeById(11));
		
	}

}
