/******************************************************************************
 * Copyright (c) 2012 didauvn.
 ******************************************************************************
 *
 ******************************************************************************
 *              M A I N T E N A N C E     L O G
 ******************************************************************************
 * ISSUE # DATE       PROGRAMMER DESCRIPTION
 * ------- ---------- ---------- ----------------------------------------------
 * 1	   08/08/2012 minhle	 Example
 ******************************************************************************
 */
package domain.attribute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.PaymentMethod;
import domain.attribute.service.PaymentMethodService;

@Controller
@RequestMapping("data/paymentmethod")
public class PaymentMethodController {

	@Autowired
	private PaymentMethodService paymentMethodService;

	

	@RequestMapping(value = "/get/{placeid}", method = RequestMethod.GET)
	@ResponseBody
	public PaymentMethod getPaymentMethod(@PathVariable("placeid") long placeId) {
		return paymentMethodService.getPaymentMethod(placeId);
	}

	@RequestMapping(value = "/update/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void editPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
		paymentMethodService.updatePaymentMethod(paymentMethod);
	}
}
