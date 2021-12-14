package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.SellingPrice;


class SellingPriceTest {
private SellingPrice sellingPrice1;



	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPriceCannotBeZero() {
IllegalArgumentException thrown = 	assertThrows(IllegalArgumentException.class, () -> {
			
	sellingPrice1 = new SellingPrice(BigDecimal.valueOf(-7), LocalDateTime.now());
		});
		
		assertEquals("Price cannot be below zero!", thrown.getMessage());
	}

}