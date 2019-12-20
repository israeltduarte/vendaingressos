package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class InvalidPriceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4046949698443828685L;

	public InvalidPriceException() {
		super("O preço deve ser maior que 0");
	}

}
