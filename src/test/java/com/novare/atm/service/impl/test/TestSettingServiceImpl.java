package com.novare.atm.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.novare.minet.model.User;
import com.novare.minet.service.IUserService;
import com.novare.minet.serviceimpl.UserServiceImpl;
import com.novare.minet.util.ServiceUtil;

@TestMethodOrder(OrderAnnotation.class)
public class TestSettingServiceImpl {

	private static IUserService userService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		userService = new UserServiceImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		userService = null;

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Order(1)
	@Test
	public void testCreateUser() throws Exception {
		User test = new User();
		test.setId(3);
		test.setFullName("Rishitha ravi");
		test.setUserName("rishi");
		test.setPassWord(ServiceUtil.encrypt("test"));

		// User already exist, return true
		assertEquals(true, userService.isValidUser(test));

		test.generateId();
		test.setFullName("Test Rishitha ravi");
		test.setUserName("abc");
		// User doesn't exist, return false
		assertEquals(false, userService.isValidUser(test));

		// User Created Successfully, SignUp
		User validUser = userService.createUser(test);
		assertEquals(ServiceUtil.encrypt("test"), validUser.getPassWord());
	}

	@Order(2)
	@Test
	public void testLogin() throws Exception {
		User test = new User();
		test.setUserName("abc");
		test.setPassWord("test");
		User validUser = userService.login(test);
		assertEquals(null, validUser);

		test.setPassWord(ServiceUtil.encrypt("test"));
		validUser = userService.login(test);
		assertNotEquals(null, validUser);

	}

	@Order(3)
	@Test
	public void testDeleteUser() throws Exception {
		User test = new User();
		test.setUserName("abc");
		test.setPassWord("test");

		// User deleted successfully, return true
		assertEquals(true, userService.deleteUser(test) != null);
	}

	@Order(4)
	@Test
	public void testIsValidUser() throws Exception {
		User test = new User();
		test.setUserName("abc");
		test.setPassWord(ServiceUtil.encrypt("test"));

		// User doesn't exist, return false
		assertEquals(false, userService.isValidUser(test));
	}

}
