package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class EmptyCreditCardData extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5169173522818108165L;

	public EmptyCreditCardData() {
		super("Cartão de crédito com dados em branco.");
	}

}
