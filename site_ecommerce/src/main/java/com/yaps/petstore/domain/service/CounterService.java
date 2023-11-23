package com.yaps.petstore.domain.service;

public interface CounterService {

	public String getUniqueId(final String name);
	
	public void deleteById(final String name);
	
}
