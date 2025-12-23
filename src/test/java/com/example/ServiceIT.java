package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;

public class ServiceIT extends TestBase {

	@Inject
	private Service service;

	@Test
	public void testMethod() {
		//when
		String result = service.method();

		//then
		assertEquals("Integration test", result);
	}
}
