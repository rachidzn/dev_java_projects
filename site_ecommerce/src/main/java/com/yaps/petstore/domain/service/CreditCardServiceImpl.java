package com.yaps.petstore.domain.service;

import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.barkbank.CreditCardStatus;
import com.yaps.petstore.domain.model.CreditCard;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.logging.Trace;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	// ======================================
	// =             Attributes             =
	// ======================================

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(CreditCardServiceImpl.class);

	// Used for logging
	private final static String _cname = CreditCardServiceImpl.class.getName();

	@Override
	public void verifyCreditCard(CreditCard creditCard) throws CheckException {
		String mname = "send";
		String result = null;
		Trace.entering(_cname, mname);
		try {
			// Encodes the data to send
			String JsonEncoded = URLEncoder.encode(creditCard.toJsonString(), "UTF-8");
			result = sendHttpRequestToBarkBank_UsingRestTemplate(JsonEncoded);
			logger.info("IN VERIF CC ......RESULT IS ... "+result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CheckException("Invalid Card");
		}
		Trace.exiting(_cname, mname);
		if( !result.equals(CreditCardStatus.VALID_CREDIT_CARD))
			throw new CheckException(result);
	}

	private String sendHttpRequestToBarkBank_UsingRestTemplate(String JsonEncoded) {
		final String SERVLET_PARAMETER = "param";
		final String URL_SERVLET_CREDITCARD = "http://localhost:8081/barkbank/creditcard";
		// Add the credit card details as param to the url
		String uri = URL_SERVLET_CREDITCARD + "?" + SERVLET_PARAMETER + "=" + JsonEncoded;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String>  entity = null;
		entity = restTemplate.getForEntity(uri, String.class);
		return entity.getBody();
	}
}
