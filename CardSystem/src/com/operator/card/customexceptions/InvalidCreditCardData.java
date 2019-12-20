package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class InvalidCreditCardData extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6651248435907980001L;

	public InvalidCreditCardData() {
		super("Cartão com dados em faltando.");
	}

}
