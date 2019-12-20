package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class MissingDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4724644752915812111L;

	public MissingDataException() {
		super("A requisição deve conter dados do cartão de crédito utilizado, o valor e o número de parcelas");
	}

}
