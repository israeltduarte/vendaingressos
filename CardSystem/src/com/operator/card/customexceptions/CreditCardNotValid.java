package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class CreditCardNotValid extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreditCardNotValid() {
		super("Cartão de crédito inválido");
	}
}
