package com.example;

import jakarta.ejb.Stateless;

@Stateless
public class Service {

	public String method() {
		return "Integration test";
	}
}
