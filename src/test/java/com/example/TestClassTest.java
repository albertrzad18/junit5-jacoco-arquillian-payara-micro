package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestClassTest {

	@Test
	public void test() {
		//given
		TestClass testClass = new TestClass();

		//when
		String result = testClass.method();

		//then
		assertEquals("Unit test", result);
	}
}
