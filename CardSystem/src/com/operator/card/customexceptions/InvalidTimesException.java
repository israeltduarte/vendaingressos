package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class InvalidTimesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7609861753665620218L;

	public InvalidTimesException() {
		super("O número de parcelas deve ser maior que 0");
	}
}
