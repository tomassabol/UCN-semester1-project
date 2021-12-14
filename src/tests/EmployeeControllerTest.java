package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Employee;
import model.EmployeeContainer;

class EmployeeControllerTest {
	private EmployeeContainer employeeCon;
	private Employee employee1;
	
	
	

	@BeforeEach
	void setUp()  {
		employeeCon = EmployeeContainer.getInstance();
		
		employee1 = new Employee(1, "081090-3221", "Tomas", "Sabol", "AleneGade", "40504432",null);
	}



	@Test
	void testFindEmployeeById() {
		employeeCon.findEmployeeById(1);
		employeeCon.addEmployee(employee1);
		
		assertEquals(employeeCon.findEmployeeById(1), employee1);
		assertNull(employeeCon.findEmployeeById(11));
		
	}

}
