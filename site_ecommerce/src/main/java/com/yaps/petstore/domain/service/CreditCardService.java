package com.yaps.petstore.domain.service;

import com.yaps.petstore.domain.model.CreditCard;
import com.yaps.petstore.exception.CheckException;

public interface CreditCardService {
	
	 public void verifyCreditCard(CreditCard creditCard) throws CheckException;

}
