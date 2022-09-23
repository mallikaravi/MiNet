package com.novare.atm.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.novare.minet.model.Product;
import com.novare.minet.service.IProductService;
import com.novare.minet.serviceimpl.ProductServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
class TestProductServiceImpl {

	private static IProductService productService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		productService = new ProductServiceImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		productService = null;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Order(1)
	@Test
	void testCreate() throws Exception {
		Product product = new Product("Apple iPhone pro 12", "APPPRO10", "It is a very good phone",
				Double.valueOf(12000), Double.valueOf(5000), 1);
		productService.create(product);
		assertNotNull(product.getId());
	}

	@Order(2)
	@Test
	void testUpdate() throws Exception {
		Product findByShortName = productService.findByShortName("APPPRO10");
		Integer id = findByShortName.getId();
		assertEquals("Apple iPhone pro 12", findByShortName.getFullName());

		findByShortName.setShortName("APP10");
		productService.update(findByShortName);
		assertEquals(id, findByShortName.getId());

		findByShortName = productService.findByShortName("APP10");
		assertEquals("APP10", findByShortName.getShortName());

	}

	@Order(3)
	@Test
	void testFindByShortName() throws Exception {
		Product findByShortName = productService.findByShortName("APP10");
		assertEquals("APP10", findByShortName.getShortName());
	}

	@Order(4)
	@Test
	void testDelete() throws Exception {
		Product findByShortName = productService.findByShortName("APP10");
		productService.delete(findByShortName);
		findByShortName = productService.findByShortName("APP10");
		assertEquals(null, findByShortName);
	}
}
