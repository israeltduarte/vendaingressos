package com.operator.card.customexceptions;

import javax.xml.ws.WebFault;

@WebFault
public class MissingDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4724644752915812111L;

	public MissingDataException() {
		super("A requisi��o deve conter dados do cart�o de cr�dito utilizado, o valor e o n�mero de parcelas");
	}

}
