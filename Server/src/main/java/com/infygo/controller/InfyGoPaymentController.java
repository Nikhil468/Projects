package com.infygo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infygo.applicationentity.CreditCard;
import com.infygo.applicationentity.Flight;
import com.infygo.service.InfyGoService;

@RestController
public class InfyGoPaymentController {
	@Autowired
	private InfyGoService service;
	
	static final String paymentSuccess = "Payment Successful";
	static final String paymentFailed = "Payment Failed";
	static final String invalidCardNumber = "Invalid Card Number. Should be of 16 digits";
	static final String invalidCVV = "Invalid CVV Number. Should be of 3 digits";
	static final String invalidExpiryMonth = "Invalid Expiry Month";
	static final String invalidCard = "Card has expired.";


	
	@RequestMapping(value="/infygo/payment" , method=RequestMethod.POST)
	public String initiatePayment(@RequestBody CreditCard card) throws ParseException {
		
		CreditCard x = new CreditCard();
		
		card.assignToCreditCard(x);
		
		String input = x.getExpiryMonth() + "/" + x.getExpiryYear(); // for example
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);
		Date expiry = simpleDateFormat.parse(input);
		boolean expired = expiry.before(new Date());
		
		if(expired) {
			return invalidCard;
		}
		
		if(card.getCardNumber().length() != 16) {
			return invalidCardNumber;
		}
		if(card.getCvv().length() != 3) {
			return invalidCVV;
		}
		
		int month = Integer.parseInt(card.getExpiryMonth());
		if(month<1 || month>12) {
			return invalidExpiryMonth;
		}
				
		boolean result = service.addCard(x);
		
		if(!result)
			return paymentFailed; 
		
		return paymentSuccess ;
	}
}
